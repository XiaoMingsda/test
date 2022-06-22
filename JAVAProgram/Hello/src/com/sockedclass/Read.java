package com.sockedclass;

import java.io.DataInputStream;
import java.io.IOException;

public class Read implements Runnable{
    DataInputStream in;
    public void setIn(DataInputStream in) {
        this.in = in;
    }
    @Override
    public void run() {
        double result = 0;
        while (true) {
            try {
                result = in.readDouble();
                System.out.println("Area = " + result);
                System.out.print("inpur r = ");
            } catch (IOException e) {
                System.out.println("与服务器已断开" + e);
                break;
            }
        }
    }
}
