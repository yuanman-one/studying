# studying Project 使用JDK1.8版本
[TOC]
# 个人的学习笔记
## 动态代理
- jdk动态代理与CGLib动态代理都是实现Spring AOP的基础。如果加入容器的目标对象有实现接口,用动态代理，如果目标对象没有实现接口,用Cglib代理
### JDK代理
    相关示例 pers.yuanman.study.proxy.JdkProxyDemo 
### CGLib动态代理
    相关示例 pers.yuanman.study.proxy.CGLibProxyDemo
### jdk和cglib动态代理原理上的区别：
- jdk动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
- 而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。

## 算法
### 二叉排序树
        pers.yuanman.study.algorithm.binarytree