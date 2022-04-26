package pers.yuanman.study.service.impl;

import pers.yuanman.study.service.AnimalActionSvc;

/**
 * 猫行为实现类
 * 继承了动物实现类
 * 实现了动物行为接口
 */
public class CatActionSvcImpl extends AnimalActionSvcImpl implements AnimalActionSvc {

    @Override
    //重写eat方法，被调用是调用改方法
    public <Cat> void eat(Cat cat) {
        //调用父类方法
        super.eat(cat);
        //自己多加的
        System.out.println(this.toString()+"  cat = " + cat);
    }

    //剩下的run和sleep方法没有重写，当被调用是调用父类方法
}
