package com.exp.exp1;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class exercise02 {
    public static void main(String[] args) {
        Vector<String> allWord, noSameWord;
        WordStatistic res = new WordStatistic();
        res.wordStatistic();
        allWord = res.getAllWord();
        noSameWord = res.getNoSameWord();
        System.out.println("(1) 一共出现了" + allWord.size() + "个单词");
        System.out.println("(2) 一共出现了" + noSameWord.size() + "个不相同的单词");
        System.out.println("按出现频率排列:");
        int[] cnt = new int[noSameWord.size()];
        for (int i = 0; i < noSameWord.size(); i++) {
            String tmp = noSameWord.elementAt(i);
            for (int j = 0; j < allWord.size(); j++) {
                if (allWord.elementAt(j).equals(tmp)) {
                    cnt[i]++;
                }
            }
        }
        for (int m = 0; m < noSameWord.size(); m++) {
            for (int n = m + 1; n < noSameWord.size(); n++) {
                if (cnt[n] > cnt[m]) {
                    String tmp = noSameWord.elementAt(m);
                    noSameWord.setElementAt(noSameWord.elementAt(n), m);
                    noSameWord.setElementAt(tmp, n);
                    int t = cnt[m];
                    cnt[m] = cnt[n];
                    cnt[n] = t;
                }
            }
        }
        for (int i = 0; i < noSameWord.size(); i++) {
            double frequency = (1.0 * cnt[i]) / allWord.size();
            System.out.printf("%s:%-7.3f", noSameWord.elementAt(i), frequency);
        }
    }
}

class WordStatistic {
    Vector<String> allWord, noSameWord;
    File file = new File("E:\\JavaFile", "english.txt");
    Scanner sc = null;
    String regex;
    WordStatistic() {
        allWord = new Vector<String>();
        noSameWord = new Vector<String>();
        regex = "[\\s\\d\\p{Punct}]+";
        try {
            sc = new Scanner(file);
            sc.useDelimiter(regex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    void setFileName(String name) {
        file = new File("E:\\JavaFile", name);
        try {
            sc = new Scanner(file);
            sc.useDelimiter(regex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void wordStatistic() {
        while (sc.hasNext()) {
            String word = sc.next();
            allWord.add(word);
            if (!noSameWord.contains(word)) {
                noSameWord.add(word);
            }
        }
    }
    public Vector<String> getAllWord() {
        return allWord;
    }
    public Vector<String> getNoSameWord() {
        return noSameWord;
    }
}
