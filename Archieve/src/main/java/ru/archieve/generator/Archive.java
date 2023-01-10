package ru.archieve.generator;

import ru.archieve.generator.gui.FolderChooser;
import ru.archieve.generator.gui.Frame;
import ru.archieve.generator.gui.listener.BtnGenerateListener;
import ru.archieve.generator.gui.listener.BtnOpenListener;
import ru.archieve.generator.gui.listener.TextFieldPathListener;
import ru.archieve.generator.service.ArchiveGenerator;
import ru.archieve.generator.service.ExcelService;
import ru.archieve.generator.service.WordService;
import javax.swing.*;
import java.awt.*;

public class Archive {
    private static JLabel labelPath;
    private static JLabel labelStatus;
    private static JTextField textFieldPath;
    private static JButton btnOpenPath;
    private static JButton btnGenerate;
    private static JPanel panelContainer;
    private static JPanel panelOpen;
    private static JPanel panelHandle;
    private static FolderChooser folderChooser;
    private static Frame frame;
    private static ArchiveGenerator archiveGenerator = new ArchiveGenerator(new ExcelService(), new WordService());

    public static void main(String[] args) {
       createGui();
    }
    private static void createGui(){
        System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");

        int width = 500;
        int height = 100;

        UIManager.put(
                "FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                "FileChooser.cancelButtonText", "Отмена");
        UIManager.put(
                "FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                "FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                "FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                "FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                "FileChooser.folderNameLabelText", "Путь директории");

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);

        labelPath = new JLabel("Open path:");
        textFieldPath = new JTextField();
        textFieldPath.setSize(new Dimension(2,30));
        btnOpenPath = new JButton();
        btnOpenPath.setText("Open");
        btnGenerate = new JButton();
        btnGenerate.setText("Generate");
        labelStatus = new JLabel();
        labelStatus.setForeground(Color.BLACK);
        folderChooser = new FolderChooser();

        textFieldPath.setFocusable(false);
        textFieldPath.setText(folderChooser.getCurrentDirectory().getAbsolutePath());
        textFieldPath.getDocument().addDocumentListener(new TextFieldPathListener(textFieldPath));

        btnOpenPath.addActionListener(new BtnOpenListener(textFieldPath, folderChooser, frame));
        btnGenerate.addActionListener(new BtnGenerateListener(btnGenerate, labelStatus, textFieldPath, archiveGenerator));

        panelOpen = new JPanel();
        panelOpen.setLayout(flowLayout);
        panelOpen.add(labelPath);
        panelOpen.add(textFieldPath);
        panelOpen.add(btnOpenPath);

        panelHandle = new JPanel();
        panelHandle.setLayout(flowLayout);
        panelHandle.add(btnGenerate);
        panelHandle.add(labelStatus);

        panelContainer = new JPanel();
        panelContainer.setSize(new Dimension(width,height-10));
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.add(panelOpen);
        panelContainer.add(panelHandle);

        frame = new Frame(width, height);
        frame.getContentPane().add(panelContainer);
        frame.setVisible(true);
    }
}
