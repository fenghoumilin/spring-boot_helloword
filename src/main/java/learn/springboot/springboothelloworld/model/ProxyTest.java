package learn.springboot.springboothelloworld.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author YCKJ2344
 * @Date 2020/5/27
 **/
//目标类接口
interface IDog{
    void run();
            }
//目标类
class GunDog implements IDog{

    @Override
    public void run() {
        System.out.println("猎狗在跑");
    }
}
class DogUtils{
    public static void method1() {
        System.out.println("增强方式一");
    }

    public static void method2() {
        System.out.println("增强方式二");
    }
}
class MyInvocationHandle implements InvocationHandler {
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtils.method1();
        method.invoke(target, args);
        DogUtils.method2();
        return null;
    }
}
//生产代理对象的工厂
class MyProxyFactory{
    public static Object getProxy(Object target) {
        MyInvocationHandle handle = new MyInvocationHandle();
        handle.setTarget(target);
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handle);
        return proxy;
    }
}
public class ProxyTest {
    public static void main(String[] args) {
        IDog dog = new GunDog();
        IDog proxy =(IDog) MyProxyFactory.getProxy(dog);
        proxy.run();
    }

}

