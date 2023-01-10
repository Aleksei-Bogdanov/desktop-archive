package ru.archieve.generator.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.archieve.generator.model.ArchFile;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchiveGenerator {
    private ExcelService excelService;
    private WordService wordService;
    private static final Logger logger = Logger.getLogger(ArchiveGenerator.class.getSimpleName() + "_logger");
    public ArchiveGenerator(ExcelService excelService, WordService wordService) {
        this.excelService = excelService;
        this.wordService = wordService;
    }
    public void generate(String path){
        XSSFWorkbook book = excelService.createWorkbook();

        String excelName = "archive_registry.xlsx";
        String excelAbsolutePath = path + "/" + excelName;

        excelService.saveExcel(book, excelAbsolutePath);

        book = excelService.openExistExcel(book, excelAbsolutePath);
        XSSFSheet sheet = book.getSheetAt(0);

        Long start = System.currentTimeMillis();
        List<ArchFile> archive = ArchiveService.getListOfArchFile(path, 0,null);

        for (ArchFile archFile:archive){
            excelService.addDataToExcel(book, sheet, archFile);
        }

        excelService.saveExcel(book, excelAbsolutePath);

        try {
            book.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Long finishExcel = System.currentTimeMillis();
        logger.log(Level.INFO,
                "Excel archive created in "
                        + (finishExcel-start) + " ms");

        wordService.createWordArchiveHierarchy(path + "\\archive_hierarchy.docx");
        Long finishWord = System.currentTimeMillis();

        logger.log(Level.INFO,
                "Word hierarchy of files created in "
                        + (finishWord-start) + " ms"
                        + "\n" + "Generated!");
    }
}
