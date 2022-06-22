package com.hspedu.pkg;

public class Student extends Person {
    private String id;
    private double score;

    public Student() {
        super();
    }

    public Student(String name, int age, String id, double score) {
        super(name, age);
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String say() {
        super.say();
        return super.say() + "id：" + this.id + " score：" + this.score + " ";
    }
}
