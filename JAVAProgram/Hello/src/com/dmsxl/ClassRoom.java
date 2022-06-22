package com.dmsxl;

public class ClassRoom implements Runnable{
    Thread student, teacher;
    public ClassRoom() {
        student = new Thread(this);
        teacher = new Thread(this);
        student.setName("张三");
        teacher.setName("王教授");
    }
    @Override
    public void run() {
        if (Thread.currentThread() == student) {
            System.out.println(student.getName() + "正在睡觉");
            try {
                Thread.sleep(1000 * 60 * 60);
            } catch (InterruptedException e) {
                System.out.println(student.getName() + "被老师叫醒了");
            }
            System.out.println(student.getName() + "开始听课");
        } else if (Thread.currentThread() == teacher) {
            for (int i = 1; i <= 3; i++) {
                System.out.println("上课" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                System.out.println("运行到这");
                student.interrupt();
            }
        }
    }
}
