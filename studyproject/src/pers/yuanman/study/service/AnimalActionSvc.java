package pers.yuanman.study.service;

/**
 * 动物的行为接口
 */
public interface AnimalActionSvc {
    /**
     * 吃
     */
    <Cat> void eat(Cat cat);

    /**
     * 跑
     */
    void  run();

    /**
     * 睡
     */
    void sleep();
}
