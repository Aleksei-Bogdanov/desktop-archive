package ru.archieve.generator.gui.listener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;

public class TextFieldPathListener implements DocumentListener {
    private JTextField textFieldPath;
    public TextFieldPathListener() {
    }

    public TextFieldPathListener(JTextField textFieldPath) {
        this.textFieldPath = textFieldPath;
    }

    private final String newline = "\n";

    public void insertUpdate(DocumentEvent e) {
        updateLog(e, "inserted into");
    }
    public void removeUpdate(DocumentEvent e) {
        textFieldPath.requestFocus();
    }
    public void changedUpdate(DocumentEvent e) {
        //Plain text components do not fire these events
    }
    public void updateLog(DocumentEvent e, String action) {
        File file = new File(textFieldPath.getText());
        String[] mass = file.list();

    }
}
