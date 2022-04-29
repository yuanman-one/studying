package pers.yuanman.study.reflect;

import pers.yuanman.study.base.model.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class对象
 */
public class ClassDemo {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
        Class<?> catClass = Cat.class;
        //Class相关方法
        //1.获取类的成员变量的信息
        //getName()获取类的全称
        String name = catClass.getName();
        System.out.println("getName()获取类的全称: "+name);
        //getSimpleName()获取类的简称
        String simpleName = catClass.getSimpleName();
        System.out.println("getSimpleName()获取类的简称: "+simpleName);
        //newInstance()方法调用默认构造器（无参数构造器）初始化新建对象
        Object newInstance = catClass.newInstance();
        System.out.println("newInstance()方法调用默认构造器（无参数构造器）初始化新建对象: "+newInstance);
        //获取成员变量获取参考如下
        //getField("成员名")获取某个公用成员的变量对象
        Field weight = catClass.getField("weight");
        System.out.println("getField(\"weight\")获取某个公用成员的变量对象: "+weight);
        //getFields()获取所有公用成员的变量对象
        Field[] fields = catClass.getFields();
        System.out.println("getFields()获取所有公用成员的变量对象: "
                + Arrays.stream(fields).map(Field::toString).collect(Collectors.joining(" \n")));
        //getDeclaredField("成员名")获取某个成员的变量对象
        Field age = catClass.getDeclaredField("age");
        System.out.println("getDeclaredField(\"age\")获取某个成员的变量对象: "+age);
        //getDeclaredFields()获取所有成员的变量对象
        Field[] declaredFields = catClass.getDeclaredFields();
        System.out.println("getDeclaredFields()获取所有成员的变量对象: "
                + Arrays.stream(declaredFields).map(Field::toString).collect(Collectors.joining(" \n")));

        //2.获得类方法
        //getMethod("方法名",参数的Class对象...)
        Method method = catClass.getMethod("add",  Integer.class);
        System.out.println("getMethod(\"方法名\",参数的Class对象...): "+method);

    }
}
