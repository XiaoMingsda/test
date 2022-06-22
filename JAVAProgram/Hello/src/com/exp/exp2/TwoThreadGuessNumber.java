package com.exp.exp2;

public class TwoThreadGuessNumber {
    public static void main(String[] args) {
        Number number = new Number();
        number.giveNumberThread.start();
        number.guessNumberThread.start();
    }
}

class Number implements Runnable {
    final int SMALLER = -1, LARGER = 1, SUCCESS = 8;
    int realNumber, guessNumber, min = 0, max = 100, message = SMALLER;
    boolean pleaseGuess = false, isGiveNumber = false;
    Thread giveNumberThread, guessNumberThread;
    Number() {
        giveNumberThread = new Thread(this);
        guessNumberThread = new Thread(this);
    }

    @Override
    public void run() {
        for (int count = 1; true; count++) {
            setMessage(count);
            if (message == SUCCESS) {
                return;
            }
        }
    }
    public synchronized void setMessage(int count) {
        //给随机数阶段
        if (Thread.currentThread() == giveNumberThread && isGiveNumber == false) {
            realNumber = (int)(Math.random() * 100) + 1;
            System.out.println("随机给你一个1至100之间的数，猜猜是多少？");
            //System.out.println("asdsa" + realNumber);
            isGiveNumber = true;//给数完毕
            pleaseGuess = true;//可以猜数
        }
        //等待猜数、判断猜数结果
        if (Thread.currentThread() == giveNumberThread) {
            while (pleaseGuess == true)//等待猜数
                try {
                    wait();
                } catch (Exception e) {

                }
            if (realNumber > guessNumber) {
                message = SMALLER;
                System.out.println("你猜小了");
            } else if (realNumber < guessNumber) {
                message = LARGER;
                System.out.println("你猜大了");
            } else {
                message = SUCCESS;
                System.out.println("恭喜，你猜对了");
            }
            pleaseGuess = true;
        }
        //给出猜测的数
        if (Thread.currentThread() == guessNumberThread && isGiveNumber == true) {
            while (pleaseGuess == false) {
                try {
                    wait();
                } catch (Exception e) {

                }
            }
            if (message == SMALLER) {
                min  = guessNumber;
                guessNumber = (min + max) / 2;
                System.out.println("我第" + count + "次猜这个数是:" + guessNumber);
            } else if (message == LARGER) {
                max = guessNumber;
                guessNumber = (min + max) / 2;
                System.out.println("我第" + count + "次猜这个数是:" + guessNumber);
            }
            pleaseGuess = false;
        }
        notifyAll();
    }
}
