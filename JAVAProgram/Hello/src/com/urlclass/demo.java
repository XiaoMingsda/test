package com.urlclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        URL url;
        Scanner sc = new Scanner(System.in);
        String mes = sc.nextLine();
        Look look = new Look();
        Thread readURL;
        try {
            url = new URL(mes);
            look.setUrl(url);
            readURL = new Thread(look);
            System.out.println("111");
            readURL.start();
        } catch (Exception e) {

        }
        System.out.println("222");
    }
}
