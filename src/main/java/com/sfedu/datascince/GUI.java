package com.sfedu.datascince;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
    private JButton inputDir = new JButton("Input Directory");
    private JButton outputDir = new JButton("Output Directory");
    private JButton run = new JButton("Run");
    private JButton singleBench = new JButton("Single benchmark process");
    private JTextField input = new JTextField("", 5);
    private JFileChooser inDir = new JFileChooser();
    private JFileChooser outDir = new JFileChooser();


    String inPath;
    String outPath;
    public GUI() {
        super("Simple Example");
        this.setBounds(100,100,400,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));

        inputDir.addActionListener(e -> {
            inDir.setDialogTitle("Выбор директории");
            // Определение режима - только каталог
            inDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = inDir.showOpenDialog(GUI.this);
            // Если директория выбрана, покажем ее в сообщении
            if (result == JFileChooser.APPROVE_OPTION ){
                JOptionPane.showMessageDialog(GUI.this, "Файл: "+ inDir.getSelectedFile());
                inPath = inDir.getSelectedFile().getPath();
            }

        });

        outputDir.addActionListener(e -> {
            outDir.setDialogTitle("Выбор директории");
            // Определение режима - только каталог
            outDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = outDir.showOpenDialog(GUI.this);
            // Если директория выбрана, покажем ее в сообщении
            if (result == JFileChooser.APPROVE_OPTION ){
                JOptionPane.showMessageDialog(GUI.this, " Файл " + outDir.getSelectedFile());
                outPath = outDir.getSelectedFile().getPath();
            }
        });
        singleBench.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setBounds(100,100,400,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });

        run.addActionListener(e -> DataUtils.benchmarkProcessor(inPath, outPath));
        container.add(inputDir);
        container.add(outputDir);
        container.add(run);
    }


    public static void main(String[] args) {
        GUI app = new GUI();
        app.setVisible(true);

    }
}
