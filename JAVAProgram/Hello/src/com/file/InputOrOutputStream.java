package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOrOutputStream {
    public static void main(String[] args) {
        /* 文件字节输入流
        File file = null;
        FileInputStream fileInputStream = null;
        try {
            file = new File("E:\\JavaFile", "Hello1.txt");
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int n = -1;
            while ((n = fileInputStream.read(bytes)) != -1) {
                String segments = new String(bytes, 0, n);
                System.out.print(segments);
            }
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println("error");
        }
         */
        File file = null;
        FileOutputStream fileOutputStream = null;
        try {
            file = new File("E:\\JavaFile", "Hello1.txt");
            fileOutputStream = new FileOutputStream(file, true);
            byte[] bytes = "Happy newYear".getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception e) {

        }
    }
}
