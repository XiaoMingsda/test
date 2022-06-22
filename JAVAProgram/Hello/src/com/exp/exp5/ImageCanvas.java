package com.exp.exp5;

import java.awt.*;

public class ImageCanvas extends Canvas {
    Image image = null;
    public ImageCanvas() {
        setSize(800, 800);
    }
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
