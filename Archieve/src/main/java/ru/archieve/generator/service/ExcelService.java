package ru.archieve.generator.service;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import ru.archieve.generator.model.ArchFile;

import java.io.*;

public class ExcelService {
    public XSSFWorkbook createWorkbook(){
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("registry");
        sheet.setColumnWidth(0, 25000);
        sheet.setColumnWidth(1, 10000);
        sheet.setColumnWidth(2, 10000);

        Row row = sheet.createRow(0);

        Cell cellDir = row.createCell(0);
        cellDir.setCellValue("Directory");

        Cell cellFolder = row.createCell(1);
        cellFolder.setCellValue("Folder");

        Cell cellFile = row.createCell(2);
        cellFile.setCellValue("File");

        CellStyle style = book.createCellStyle(); //Create new style
        style.setWrapText(true); //Set wordwrap

        return book;
    }
    public void saveExcel(XSSFWorkbook book, String excel){
        try {
            FileOutputStream outputStream = new FileOutputStream(excel);
            book.write(outputStream);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public XSSFWorkbook openExistExcel(XSSFWorkbook book, String excel){
        try {
            book = new XSSFWorkbook(new FileInputStream(excel));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }
    public void addDataToExcel(XSSFWorkbook book, XSSFSheet sheet, ArchFile archFile){
        int lastRow = sheet.getLastRowNum();
        Row row = sheet.createRow(++lastRow);
        //add folder directory name to cell 0
        Cell cellDir = row.createCell(0);
        cellDir.setCellValue(archFile.getDirName());
        //add folder name and directory path to cell 1
        createHyperLink(book, archFile.getDirPath(), archFile.getFolderName(), row, 1);
        //add file name and path to cell 2
        createHyperLink(book, archFile.getFilePath(), archFile.getFileName(), row, 2);
    }
    private void createHyperLink(XSSFWorkbook book, String path, String name, Row row, int col){
        XSSFCellStyle linkstyle = book.createCellStyle();
        XSSFFont linkfont = book.createFont();
        linkfont.setUnderline(XSSFFont.U_SINGLE);
        linkfont.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        linkstyle.setFont(linkfont);

        CreationHelper createHelper = book.getCreationHelper();
        XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.FILE);

        XSSFCell cellHyperlink = (XSSFCell) row.createCell(col);
        File file = new File(path);
        cellHyperlink.setCellValue(name);
        link.setAddress(file.toURI().toString());

        cellHyperlink.setHyperlink(link);
        cellHyperlink.setCellStyle(linkstyle);
    }
}
