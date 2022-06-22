package com.exp.exp2;

import java.util.Scanner;

public class TypeKey {
    public static void main(String[] args) {
        System.out.println("键盘练习(输入#结束程序)");
        System.out.println("输入显示的字母(回车)\n");
        Letter letter;
        letter = new Letter();
        GiveLetterThread giveLetterThread;
        InputLetterThread inputLetterThread;
        giveLetterThread = new GiveLetterThread();
        giveLetterThread.setLetter(letter);
        giveLetterThread.setSleepLength(3200);
        inputLetterThread = new InputLetterThread();
        inputLetterThread.setLetter(letter);
        giveLetterThread.start();
        inputLetterThread.run();
    }
}

class Letter {
    char c = '\0';

    public void setC(char c) {
        this.c = c;
    }
    public char getC() {
        return c;
    }
}

class GiveLetterThread extends Thread {
    Letter letter;
    char startChar = 'a', endChar = 'z';
    int sleepLength = 5000;

    public void setLetter(Letter letter) {
        this.letter = letter;
    }
    public void setSleepLength(int sleepLength) {
        this.sleepLength = sleepLength;
    }
    @Override
    public void run() {
        char c = startChar;
        while (true) {
            letter.setC(c);
            System.out.printf("显示的字符:%c\n", letter.getC());
            try {
                Thread.sleep(sleepLength);
            } catch (Exception e) {

            }
            c = (char)(c + 1);
            if (c > endChar) {
                c = startChar;
            }
        }
    }
}

class InputLetterThread extends Thread {
    Scanner scanner;
    Letter letter;
    int score = 0;

    InputLetterThread() {
        scanner = new Scanner(System.in);
    }
    public void setLetter(Letter letter) {
        this.letter = letter;
    }
    @Override
    public void run() {
        while (true) {
            String str = scanner.nextLine();
            char c = str.charAt(0);
            if (c == letter.getC()) {
                score++;
                System.out.printf("\t\t输入对了，目前分数%d\n", score);
            } else {
                System.out.printf("\t\t输入错了，目前分数%d\n",score);
            }
            if (c == '#') {
                System.exit(0);
            }
        }
    }
}
