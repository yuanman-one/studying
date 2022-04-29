package pers.yuanman.study.proxy;

import pers.yuanman.study.base.factory.ProxyFactory;
import pers.yuanman.study.base.model.Cat;
import pers.yuanman.study.base.model.Dog;
import pers.yuanman.study.base.service.InvocationHandlerImpl;
import pers.yuanman.study.service.AnimalActionSvc;
import pers.yuanman.study.service.impl.CatActionSvcImpl;
import pers.yuanman.study.service.impl.DogActionSvcImpl;

/**
 * CGlib代理示例
 * JDK动态代理需要被代理类实现接口，如果被代理类没有实现接口，那么这么实现动态代理？
 * 这时候就需要用到CGLib了。这种代理方式就叫做CGlib代理。
 * Cglib代理也叫作子类代理，他是通过在内存中构建一个子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，
 * 然后加入自己需要的操作。因为使用的是继承的方式，所以不能代理final 类。
 */
public class CGlibProxyDemo {

    public static void main(String[] args) {
        Dog dog = new Dog("狗狗",2,"橘色");

        //代理的真实对象
        DogActionSvcImpl dogActionSvcImpl = new DogActionSvcImpl();
        /**
         * 构建拦截器
         * ProxyFactory 实现了 MethodInterceptor 接口
         */
        ProxyFactory proxyFactory = new ProxyFactory(dogActionSvcImpl);
        //获取代理对象 即生成被代理对象的子类
        DogActionSvcImpl proxyInstance = proxyFactory.newProxyInstance(dogActionSvcImpl);

        //调用方法，实际上调用后被proxyFactory拦截器拦截后，调用proxyFactory里面的invoke方法
        proxyInstance.eat(dog);
        System.out.println("-------------");
        proxyInstance.run();
        System.out.println("-------------");
        proxyInstance.sleep();
        System.out.println("-------------");
    }
}
