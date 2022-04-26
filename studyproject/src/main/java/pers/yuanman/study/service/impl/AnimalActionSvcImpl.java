package pers.yuanman.study.service.impl;

import pers.yuanman.study.service.AnimalActionSvc;

/**
 * 动物行为实现类
 * 实现了动物行为接口
 */
public class AnimalActionSvcImpl implements AnimalActionSvc {


    @Override
    public <Cat> void eat(Cat cat) {
        System.out.println(this.toString() + ":  cat = " + cat);
    }
    @Override
    public void run() {
        System.out.println("动物跑步");
    }
    @Override
    public void sleep() {
        System.out.println("动物睡觉");

    }
}
