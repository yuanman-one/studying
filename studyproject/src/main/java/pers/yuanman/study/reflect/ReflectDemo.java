package pers.yuanman.study.reflect;

import pers.yuanman.study.base.model.Cat;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * java反射示例
 * 有是三种反射方式，三种方式创建的Class对象都是同一个
 * 反射会额外的消耗一定的系统资源,如果不需要动态的创建一个对象,那么就不需要用反射
 * 下面是与反射相关的类
 * Class类 代表类的实体，在运行的Java应用程序中表示类和接口
 * Field类 代表类的成员变量(成员变量也称为类的属性)
 * Method类 代表类的方法
 * Constructor类 代表类的构造方法
 */
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //三种反射方式演示方法
        //1.全类名加载，将字节码文件加载进内存，返回class对象
        Class<?> aClass1 = Class.forName("pers.yuanman.study.base.model.Cat");
        //2.类名.class，通过类名的属性class获取
        Class<Cat> aClass2 = Cat.class;
        //3.对象.getClass；getClass在Object中定义着
        Cat cat = new Cat();
        Class<? extends Cat> aClass3 = cat.getClass();

        System.out.println(aClass1);
        System.out.println(aClass2);
        System.out.println(aClass3);
        System.out.println(aClass3.newInstance());

        //==比较三个对象
        System.out.println(aClass1==aClass2);
        System.out.println(aClass1==aClass3);
        System.out.println("-------------------分割线1------------------------");
        Class<?> catClass = Cat.class;
        //通过class对象创建对象实例
        Object object = catClass.newInstance();
        System.out.println(object);
        Cat cat1 =(Cat)object;



    }
}
