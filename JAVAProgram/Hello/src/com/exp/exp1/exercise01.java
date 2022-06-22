package com.exp.exp1;

import java.io.*;
import java.util.Scanner;

public class exercise01 {
    public static void main(String[] args) {
        File afile = null;
        File bfile = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            afile = new File("E:\\JavaFile", "score.txt");
            bfile = new File("E:\\JavaFile", "scoreAnalysis.txt");
            fileReader = new FileReader(afile);
            fileWriter = new FileWriter(bfile);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            String str = null;
            double total = 0;
            //读取文件的内容
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
                total += Fenxi.getTotalScore(str);
                //写到另一个文件
                String tmp = str + " 总成绩" + total + "分";
                bufferedWriter.write(tmp);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
            fileReader.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("读取文件失败");
        }
    }
}

class Fenxi {
    public static double getTotalScore(String s) {
        double total = 0;
        Scanner sc = new Scanner(s);
        sc.useDelimiter("[^0123456789.]+");
        while (sc.hasNext()) {
            try {
                double score = sc.nextDouble();
                total += score;
            } catch (Exception e) {
                String tmp = sc.next();
            }
        }
        return total;
    }
}
