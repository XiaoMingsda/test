package com.inetaddressclass;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Arrays;

public class demo {
    public static void main(String[] args) {
        byte[] b = new byte[1];
        b = "abcasdadasdasdasdasdasdasd".getBytes();
        System.out.println(Arrays.toString(b));
        /*
        ServerSocket serverSocket = null;
        Socket sc = null;
        try {
            serverSocket = new ServerSocket(2010);
        } catch (IOException e) {}
        try {
            sc = serverSocket.accept();
        } catch (IOException e) {

        }
        */

        /*
        try {
            //获取域名为 www.sina.com.cn 的IP地址
            InetAddress byName = InetAddress.getByName("www.sina.com.cn");
            System.out.println(byName.toString());
            //获取本机的IP地址
            byName = InetAddress.getByName("");
            System.out.println(byName.toString());
            //获取本机的IP地址
            byName = InetAddress.getLocalHost();
            //获取本机的域名
            System.out.println(byName.getHostName());
            //获取本机的IP地址
            System.out.println(byName.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
         */
        try {

        } catch(Exception e) {

        }
    }
}
