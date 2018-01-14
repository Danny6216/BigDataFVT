package com.sfedu.datascince;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        try {
            readBenchmark("/home/dann/Documents/bigData//Benchmark_1_sniff_1.txt");
        }catch (Exception ex){

        }



//        try{
//            FileInputStream fstream = new FileInputStream("C:/CyberMouse/Benchmark_1/Benchmark_1_sniff_1.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//            String strLine;
//            while ((strLine = br.readLine()) != null){
//                System.out.println(strLine);
//            }
//        }catch (IOException e){
//            System.out.println("Ошибка");
//        }
    }
    public static void readBenchmark(String inPath/*, String outPath*/) {
        try {

            ArrayList<Double> middle = new ArrayList<Double>();
            Double[] foo = new Double[11];
            for(int i = 0; i < foo.length; i++){
                foo[i] = 0.0;
            }
            ArrayList<Integer> exclusions = new ArrayList<Integer>();
//            exclusions.add(10);
            FileInputStream fstream = new FileInputStream(inPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//            StringBuilder stringBuilder = new StringBuilder();
//            String ls = System.getProperty("line.separator");
            String strLine;
            int columnCount = 0;
            while ((strLine = br.readLine()) != null) {
                columnCount++;
                String[] str = strLine.replace(",",".").split(" ");
                ArrayList<Double> out = new ArrayList();
                for (int i = 0; i < str.length; i++) {
//                    if (!exclusions.contains(i)){
                        out.add(Double.parseDouble(str[i]));
//                    }
                    foo[i] += Double.parseDouble(str[i]);
                }
                for (double item : out){
//                    System.out.print(item + "; ");
                }
//                for( int i = 0; i < out.size(); i++){
//                    Double foo = middle.get(i) + out.get(i);
//                    middle.set(i, foo);
//                }
                System.out.println();
            }
            for(Double item : foo){
                System.out.print((item / columnCount) + ", ");
            }

        }
        catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
    private static Double[] arrayInit(int size){
        Double[] foo = new Double[size];
        for (Double item : foo){
            item = 0.0;
        }
        return foo;
    }
}
