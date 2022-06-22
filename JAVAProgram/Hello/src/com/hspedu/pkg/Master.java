package com.hspedu.pkg;

public class Master {
    /*
    public void feed(Cat cat, Rice rice) {
        System.out.println(cat.say() + "吃" + rice.eat());
    }
    public void feed(Dog dog, Bone bone) {
        System.out.println(dog.say() + "吃" + bone.eat());
    }*/
    public void feed(Animal animal, Food food) {
        System.out.println(animal.say() + "吃" + food.eat());
    }
}
