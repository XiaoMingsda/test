package com.hspedu.homework;

public class Homework07 {
    public static void main(String[] args) {
        Car1 car1 = new Car1(45);
        Car1.Air air = car1.new Air();
        air.flow();
        car1.setTemperature(-1);
        air.flow();
        car1.setTemperature(24);
        air.flow();
    }
}

class Car1 {
    private int temperature;

    public Car1(int temperature) {
        this.temperature = temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public class Air {
        public void flow() {
            int temp = Car1.this.temperature;
            if (temp > 40) {
                System.out.println("吹冷气");
            } else if (temp < 0) {
                System.out.println("吹暖气");
            } else {
                System.out.println("关掉空调");
            }
        }
    }
}