package ru.archieve.generator.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class WordService {
    public WordService() {
    }
    public void createWordArchiveHierarchy(String path) {
        File file = new File(path);
        List<String> records = PrintService.getListFilesInfo(file.getParentFile().getPath(), "");
        FileOutputStream fos = null;
        XWPFDocument document = null;
        try {
            fos = new FileOutputStream(file);
            document = new XWPFDocument();
            for (String rec : records) {
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setSpacingBetween(1);
                paragraph.setSpacingAfter(1);
                XWPFRun xwpfRun = paragraph.createRun();
                xwpfRun.setFontSize(12);
                xwpfRun.setFontFamily("Times New Roman");
                xwpfRun.setText(rec);
            }
            document.write(fos);
            fos.close();
        } catch (IOException e) {
            Logger.getLogger(WordService.class.getSimpleName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
