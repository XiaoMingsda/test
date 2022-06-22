package com.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderOrFileWrite {
    public static void main(String[] args) {
        char[] c = new char[1024];
        File afile = null;
        File bfile = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            afile = new File("E:\\JavaFile", "Hello1.txt");
            bfile = new File("E:\\JavaFile", "Hello3.txt");
            fileReader = new FileReader(afile);
            fileWriter = new FileWriter(bfile);
            int n = -1;
            while ((n = fileReader.read(c)) != -1) {
                fileWriter.write(c, 0, n);
                fileWriter.flush();
                fileReader.close();
                fileWriter.close();
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
