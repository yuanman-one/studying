package pers.yuanman.study.service.impl;

import pers.yuanman.study.service.AnimalActionSvc;

/**
 * 狗行为实现类
 */
public class DogActionSvcImpl  {

    public <Dog> void eat(Dog dog) {
        System.out.println(this.toString() + ":  Dog = " + dog);
    }

    public void run() {
        System.out.println("动物跑步");
    }

    public void sleep() {
        System.out.println("动物睡觉");

    }
}
