package com.exp.exp1;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class exercise03 {
    public static void main(String[] args) {
        File file = new File("book.zip");
        File dir = new File("mybook");
        byte[] b = new byte[100];
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry zipEntry = null;//用于获取压缩文件中的文件或文件夹
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                File file1 = new File(dir, zipEntry.getName());
                FileOutputStream outputStream = new FileOutputStream(file1);
                int n = -1;
                System.out.println(file1.getAbsolutePath() + "的内容：");
                while ((n = zipInputStream.read(b, 0, 100)) != -1) {
                    String str = new String(b, 0, n);
                    System.out.println(str);
                    outputStream.write(b, 0, n);
                }
                outputStream.close();
            }
            zipInputStream.close();
        } catch (Exception e) {
            //System.out.println(e);
        }
    }
}
