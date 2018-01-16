package com.sfedu.datascince;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
    private JRadioButton radio1 = new JRadioButton("Один файл");
    private JRadioButton radio2 = new JRadioButton("Все файлов");

    private JButton inputDir = new JButton("Input Directory");
    private JButton outputDir = new JButton("Output Directory");
    private JButton run = new JButton("Матрица Пирсона");

    private JFileChooser inDir = new JFileChooser();
    private JFileChooser outDir = new JFileChooser();


    String inPath;
    String outPath;
    public GUI() {
        super("Simple Example");
        this.setBounds(100,100,400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4,1,2,2));
        run.setSize(20, 30);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        container.add(radio1);

        radio1.setSelected(true);
        container.add(radio2);

        inputDir.addActionListener(e -> {
            if(radio1.isSelected()){
                inDir.setDialogTitle("Выбор файла");
                inDir.setFileSelectionMode(JFileChooser.FILES_ONLY);

            }else {
                inDir.setDialogTitle("Выбор директории");
                inDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }

            int result = inDir.showOpenDialog(GUI.this);
            if (result == JFileChooser.APPROVE_OPTION ){
                JOptionPane.showMessageDialog(GUI.this, "Файл: "+ inDir.getSelectedFile());
                inPath = inDir.getSelectedFile().getPath();
            }
        });

        outputDir.addActionListener(e -> {

                outDir.setDialogTitle("Выбор файла");
                outDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = outDir.showOpenDialog(GUI.this);
            if (result == JFileChooser.APPROVE_OPTION ){
                JOptionPane.showMessageDialog(GUI.this, " Файл " + outDir.getSelectedFile());
                outPath = outDir.getSelectedFile().getPath();
            }
        });


        run.addActionListener(e -> {
            if(radio1.isSelected()){
                DataUtils.singleFileProcess(inPath, outPath);
            }else {
                DataUtils.benchmarkProcessor(inPath, outPath);
            }
        });
        container.add(inputDir);
        container.add(outputDir);
        container.add(run);
//        container.add(singleInputDir);
//        container.add(singleOutputDir);
//        container.add(singleBench);
    }


    public static void main(String[] args) {
        GUI app = new GUI();
        app.setVisible(true);

    }
}
