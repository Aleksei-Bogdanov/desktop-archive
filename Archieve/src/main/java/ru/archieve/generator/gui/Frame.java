package ru.archieve.generator.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
    }
    public Frame(int width, int height) {
        this.setTitle("Archivist");
        this.getContentPane().setPreferredSize(new Dimension(width,height));
        setDefaultLookAndFeelDecorated(true);
        this.pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
