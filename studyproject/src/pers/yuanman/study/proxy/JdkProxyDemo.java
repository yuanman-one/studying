package pers.yuanman.study.proxy;
import pers.yuanman.study.base.model.Cat;
import pers.yuanman.study.base.service.InvocationHandlerImpl;
import pers.yuanman.study.service.AnimalActionSvc;
import pers.yuanman.study.service.impl.AnimalActionSvcImpl;
import pers.yuanman.study.service.impl.CatActionSvcImpl;

import java.lang.reflect.Proxy;

/**
 * JDK代理
 * 代理实现类，这个类必须实现一个接口
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        Cat cat = new Cat("兰兰",1,"橘色");

        //代理的真实对象
        AnimalActionSvc catActionSvc = new CatActionSvcImpl();
        /**
         * 构建动态代理类
         * InvocationHandlerImpl 实现了 InvocationHandler 接口，并能实现方法调用从代理类到委托类的分派转发
         * 其内部通常包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用.
         * 即：要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法
         */
        InvocationHandlerImpl handler = new InvocationHandlerImpl(catActionSvc);
        //获取代理对象
        AnimalActionSvc proxyInstance = handler.newProxyInstance(catActionSvc);
        //调用方法，实际上调用的都是InvocationHandlerImpl里面的invoke方法
        proxyInstance.eat(cat);
        System.out.println("-------------");
        proxyInstance.run();
        System.out.println("-------------");
        proxyInstance.sleep();
        System.out.println("-------------");
    }
}
