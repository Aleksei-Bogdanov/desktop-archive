package ru.archieve.generator.gui.listener;

import ru.archieve.generator.gui.Frame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnOpenListener implements ActionListener {
    private JTextField textFieldMsgPath;
    private JFileChooser fileChooserMsg;
    private Frame frame;

    public BtnOpenListener() {
    }

    public BtnOpenListener(JTextField textFieldMsgPath, JFileChooser fileChooserMsg, Frame frame) {
        this.textFieldMsgPath = textFieldMsgPath;
        this.fileChooserMsg = fileChooserMsg;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooserMsg.setDialogTitle("Выбор директории");
        fileChooserMsg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooserMsg.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            textFieldMsgPath.setText(fileChooserMsg.getSelectedFile().getAbsolutePath());
        }
    }
}
