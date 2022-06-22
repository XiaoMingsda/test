package com.hspedu.homework;

public class Homework08 {
    public static void main(String[] args) {
        Color[] colors = Color.values();
        for (Color color: colors) {
            switch (color) {
                case RED:
                    System.out.print("红色 ");
                    break;
                case BLUE:
                    System.out.print("蓝色 ");
                    break;
                case BLACK:
                    System.out.print("黑色 ");
                    break;
                case YELLOW:
                    System.out.print("黄色 ");
                    break;
                case GREEN:
                    System.out.print("绿色 ");
                    break;
            }
            color.show();
        }
    }
}

enum Color implements ShowColor{
    RED(255,0,0),
    BLUE(0,0,255),
    BLACK(0,0,0),
    YELLOW(255,255,0),
    GREEN(0,255,0);

    private int redValue;
    private int greenValue;
    private int blueValue;

    private Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public void show() {
        System.out.println("redValue:" + redValue + " greenValue:"
                            + greenValue + " blueValue:" + blueValue);
    }
}

interface ShowColor {
    void show();
}