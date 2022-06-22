package com.hspedu.stringbuffer_;

import java.util.Scanner;

public class StringBufferExercise02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            System.out.print("商品名称：");
            String name = scanner.next();
            stringBuffer.append(name);
            stringBuffer.append("\t\t");
            System.out.print("商品价格：");
            StringBuffer s = new StringBuffer(scanner.next());
            int indexOf = s.indexOf(".");
            for (int i = indexOf - 3; i > 0; i -= 3) {
                s.insert(i,',');
            }
            stringBuffer.append(s);
            stringBuffer.append("\n");
            System.out.println("商品名\t商品价格");
            System.out.println(stringBuffer);
        }
    }
}
