package com.urlclass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Look implements Runnable{
    URL url;
    public void setUrl(URL url) {
        this.url = url;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = url.openStream();
            byte[] b = new byte[1024];
            int n = -1;
            while ((n = inputStream.read(b)) != -1) {
                String tmps = new String(b, 0, n);
                System.out.print(tmps);
            }
        } catch (Exception e) {

        }
    }
}
