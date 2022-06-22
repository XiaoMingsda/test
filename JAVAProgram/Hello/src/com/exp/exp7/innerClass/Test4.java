package com.exp.exp7.innerClass;

public class Test4 {
    public static void main(String[] args) {

        ShowBoard showBoard = new ShowBoard();
        showBoard.showMess(new OutputAlphabet() {});
        showBoard.showMess(new OutputEnglish() {});
        showBoard.showMess(new OutputAlphabet() {
            @Override
            public void output() {
                System.out.println("3");
            }
        });


        /*
        HelloMachine helloMachine = new HelloMachine();
        helloMachine.turnOn(new SpeakHello() {
            @Override
            public void speak() {
                System.out.println("hello, you are welcome!");
            }
        });

        helloMachine.turnOn(new SpeakHello() {
            @Override
            public void speak() {
                System.out.println("你好， 欢迎光临！");
            }
        });
         */

        /*
        MyOuterClass4 myOuterClass4 = new MyOuterClass4();
        myOuterClass4.testOut();
         */
    }
}

class OutputAlphabet {
    public void output() {
        System.out.println("1");
    }
}
class OutputEnglish extends OutputAlphabet {
    @Override
    public void output() {
        System.out.println("2");
    }
}
class ShowBoard {
    public void showMess(OutputAlphabet show) {
        show.output();
    }
}


/*
interface SpeakHello {
    void speak();
}

class HelloMachine {
    public void turnOn(SpeakHello hello) {
        hello.speak();
    }
}
*/

/*
interface IMyInterface {
    void m1();
    void m2();
}
class A {
    void mm() {}
    void nn() {
        System.out.println(" 我是 A类 中的NN");
    }
}
class MyOuterClass4 {
    public void testOut() {
        new IMyInterface() {
            @Override
            public void m1() {
                System.out.println(" 实现m1 ");
            }
            @Override
            public void m2() {
                System.out.println(" 实现m2 ");
            }
        }.m1();
        new A() {
            @Override
            void mm() {
                System.out.println("override");
            }
        }.nn();
    }
}
 */