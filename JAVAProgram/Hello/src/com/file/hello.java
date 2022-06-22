package com.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class hello {
    public static void main(String[] args) {
        File file = new File("E:\\", "JavaFile");
        if (!file.exists()) {
            file.mkdir();
        }
        //以字符串形式返回目录下的全部文件
        String[] fileList = file.list();
        for (String s:fileList) {
            System.out.println(s);
        }
        System.out.println();
        //用File对象形式返回目录下的全部文件
        File[] files = file.listFiles();
        for (File f:files) {
            System.out.println(f);
        }
        System.out.println();
        //列出指定类型的文件
        FileAccept fileAccept = new FileAccept();
        fileAccept.setExtendName("java");
        String[] strings = file.list(fileAccept);
        for (String s:strings) {
            System.out.println(s);
        }
    }
}

class FileAccept implements FilenameFilter {
    private String extendName;
    public void setExtendName(String s) {
        extendName = "." + s;
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extendName);
    }
}
