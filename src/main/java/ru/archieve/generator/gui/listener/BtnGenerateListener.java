package ru.archieve.generator.gui.listener;
import ru.archieve.generator.service.ArchiveGenerator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BtnGenerateListener implements ActionListener {
    private JButton btnGenerate;
    private JLabel labelStatus;
    private JTextField textFieldPath;
    private ArchiveGenerator archiveGenerator;
    public BtnGenerateListener(JButton btnGenerate, JLabel labelStatus, JTextField textFieldPath, ArchiveGenerator archiveGenerator) {
        this.btnGenerate = btnGenerate;
        this.labelStatus = labelStatus;
        this.textFieldPath = textFieldPath;
        this.archiveGenerator = archiveGenerator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (labelStatus.getText().equals("generated!") ){
            labelStatus.setText("");
        }
        btnGenerate.setEnabled(false);
        labelStatus.setText("generating...");
        archiveGenerator.generate(textFieldPath.getText());
        labelStatus.setText("generated!");
        btnGenerate.setEnabled(true);
    }
}
