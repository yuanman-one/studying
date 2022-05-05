package pers.yuanman.study.base.factory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 创建代理工厂类ProxyFactory
 * 实现cglib方法拦截器类MethodInterceptor
 */
public class ProxyFactory implements MethodInterceptor {

    private Object obj;

    public ProxyFactory(Object obj) {
        this.obj = obj;
    }

    /**
     *  拦截方法
     * @param obj         cglib生成的代理对象
     * @param method      被代理对象的方法
     * @param args        方法入参
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");

        // 执行目标对象的方法
        //Object result = method.invoke(this.obj, args);
        Object result = methodProxy.invokeSuper(obj, args);

        System.out.println("提交事务...");
        return result;
    }

    /**
     * 创建代理对象
     *
     * @param t   被代理对象
     * @param <T>
     * @return 代理对象
     */
    public <T> T newProxyInstance(T t) {
        // 1.cglib工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(this.obj.getClass());
        // 3.设置回调函数  放入拦截器
        en.setCallback(this);
        //创建代理类并返回
        return (T) en.create();
    }

    /**
     * 创建代理对象
     *
     */
    public  Object newProxyInstance() {
        // 1.cglib工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(this.obj.getClass());
        // 3.设置回调函数  放入拦截器
        en.setCallback(this);
        //创建代理类并返回
        return en.create();
    }
}

