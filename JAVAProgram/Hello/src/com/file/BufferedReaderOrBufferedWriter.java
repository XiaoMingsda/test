package com.file;

import java.io.*;
import java.util.StringTokenizer;

public class BufferedReaderOrBufferedWriter {
    public static void main(String[] args) {
        File afile = null;
        File bfile = null;
        Reader reader = null;
        Writer writer = null;
        try {
            afile = new File("E:\\JavaFile", "Hello4.txt");
            reader = new FileReader(afile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tmp = null;
            bfile = new File("E:\\JavaFile", "Hello5.txt");
            writer = new FileWriter(bfile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            while ((tmp = bufferedReader.readLine()) != null) {
                StringTokenizer fenxi = new StringTokenizer(tmp);
                int count = fenxi.countTokens();
                tmp = tmp + " 句子中单词个数：" + count;
                bufferedWriter.write(tmp);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();
            reader = new FileReader(bfile);
            bufferedReader = new BufferedReader(reader);
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
