package com.exp.exp3;

import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int waitTime = 500;
        for (int i = 0; i < 10; i++) {
            String name = "线程" + i;
            int time = random.nextInt(1000);
            waitTime += time;
            Runnable runner = new ExecutorThread(name, time);
            System.out.println("增加：" + name + "/" + time);
            executorService.execute(runner);
        }
        try {
            Thread.sleep(waitTime);
            executorService.shutdown();
            executorService.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {}
    }
}

class ExecutorThread implements Runnable {
    private final String name;
    private final int delay;
    public ExecutorThread(String name, int delay) {
        this.name = name;
        this.delay = delay;
    }
    @Override
    public void run() {
        System.out.println("启动：" + name);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {}
        System.out.println("完成：" + name);
    }
}
