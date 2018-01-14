package com.sfedu.datascince;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        try {
            String inPath = "/home/dann/Documents/bigData/";
            String outPath = "/home/dann/Documents/bigData/output/";
            benchmarkProcessor(2, inPath, outPath);
        }catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
    }
    public static void benchmarkProcessor(int benchmark, String inPath, String outPath){
        try {
            String filename = outPath + "bench_" + benchmark +"_out.xlsx" ;
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Sheet");
            for (int k = 1; k <= 73; k++) {
                double[][] bench = bench2arr(inPath + "Benchmark_" + benchmark + "_sniff_" + k + ".txt");
                List<Double>list = arrayToCorrelMatrix(bench);
                System.out.println("result: Benchmark_" + benchmark + "_sniff_" + k);
//                for(Double item : list){
//                    System.out.print(String.format("%.2f",item) + "; ");
//                }
                Row row = sheet.createRow(k);
                row.createCell(0).setCellValue("Benchmark " + benchmark + " sniff " + k);
                for(int i = 0; i < list.size(); i++){

                    row.createCell(i+1).setCellValue(list.get(i));
                }
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.dispose();
            System.out.println("Your excel file has been generated!" + filename);
        }catch (Exception ex){
            System.out.println("Exception: " + ex);
        }

    }
    public static  double[][] bench2arr(String inPath){
        try {
            double[][] foo = new double[8][2049];
            FileInputStream fstream = new FileInputStream(inPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int j = 0;
            while ((strLine = br.readLine()) != null) {
                double[] str = Arrays.stream(strLine.replace(",",".").split(" ")).mapToDouble(Double::parseDouble).toArray();
                for(int i = 0; i < foo.length; i++){
                    foo[i][j] = str[i];
                    foo[i][foo[1].length-1] += foo[i][j];
                }
                j++;
            }
            for(int i = 0; i < foo.length; i++){
                foo[i][foo[1].length-1] = foo[i][foo[1].length-1] / (foo[1].length-1);
            }

            return foo;
        } catch (Exception ex){
            System.out.println("Exception: " + ex);
            return null;
        }
    }
    public static List<Double> arrayToCorrelMatrix(double[][] arr){
        List<Double> result = new ArrayList<>();
        for(int i = 0; i < arr[1].length-1; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j][i] = arr[j][i] - arr[j][arr[1].length-1];
            }
        }
        int t = 0;
        int j = arr.length - 1;
        while (t < j){
            for(int i = t+1; i < arr.length; i++){
                result.add(correlCoef(arr, t, i));
            }
            t++;
        }
        return result;
    }

    public static double correlCoef(double[][] arr, int x, int y){
        double sumA = 0;
        double sumB = 0;
        double sumC = 0;
        for(int i = 0; i < arr[1].length; i++){
            sumA += (arr[x][i] * arr[y][i]);
            sumB += arr[x][i] * arr[x][i];
            sumC += arr[y][i] * arr[y][i];
        }
        return (sumA / (Math.sqrt(sumB * sumC)));
    }
}
