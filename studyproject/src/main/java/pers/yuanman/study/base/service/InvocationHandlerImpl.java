package pers.yuanman.study.base.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 调用处理器实现类
 * 每次生成动态代理类对象时都需要指定一个实现了该接口的调用处理器对象
 * 在使用动态代理类时，我们必须实现InvocationHandler接口
 */
public class InvocationHandlerImpl implements InvocationHandler {
    /**
     * 需要代理的类(即被代理类)
     */
    private Object obj;

    public InvocationHandlerImpl(Object obj) {
        this.obj = obj;
    }

    /**
     * @param proxy  代表动态代理对象
     * @param method 代表正在执行的方法
     * @param args   代表调用目标方法时传入的实参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("在调用之前，我要干点啥呢？比如记录日志");
        System.out.println("Method:" + method);
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(obj, args);
        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("在调用之后，我要干点啥呢？");
        return result;
    }

    /**
     * 生成获取代理类
     *
     * @param t   强转类型对象(即实现类的实现接口的对象)
     * @param <T>
     * @return T
     */
    public <T> T newProxyInstance(T t) {
        //获取动态代理对象（这个才是真正能被使用的）
        //需要的三个参数分别是：目标类的类构建器，目标类的实现接口，刚刚构建的动态代理类
        Object newProxyInstance = Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                this);
        return (T) newProxyInstance;
    }

    /**
     * 生成获取代理类
     *
     */
    public  Object newProxyInstance() {
        //获取动态代理对象（这个才是真正能被使用的）
        //需要的三个参数分别是：目标类的类构建器，目标类的实现接口，刚刚构建的动态代理类
        Object newProxyInstance = Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                this);
        return  newProxyInstance;
    }
}
