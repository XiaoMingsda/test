package com.file;

import java.io.File;

public class helloRunTime {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            File file = new File("D:\\360Downloads\\Software\\屏幕拾色器", "MyGetColor.exe");
            runtime.exec(file.getAbsolutePath());
        } catch (Exception e) {

        }
    }
}
