package com.harry.myapplication.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2021/7/14.
 *
 * @author harry
 */
public interface Massage {
    void massage();

    public static void main(String[] args) {
        // 静态代理
        Massage massage = new Lucy();
        Agent agent = new Agent(massage);
        agent.massage();

        Alvin alvin = new Alvin();
        // 动态代理
        Object o = Proxy.newProxyInstance(Massage.class.getClassLoader(),
                new Class[]{Massage.class, Wash.class},
                new InvocationHandler() {
            // 参数1：代理对象。参数2：执行的方法。参数3：方法的参数。
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 调用方法时会回调
                        System.out.println("invoke");

                        // 在这里不能调用参数一的方法，会死循环导致StackOverflowError
                        // print一个对象，默认会调用对象的toString()
//                        System.out.println(proxy);

//                        return null;
                        // invoke第一个参数是要执行方法的对象（要代理的对象），第二个参数是方法的参数
                        return method.invoke(alvin, args);
                    }
                });

        Massage mass = (Massage) o;
        mass.massage();

        Wash wash = (Wash) o;
        wash.wash();
    }

    public static class Lucy implements Massage {

        @Override
        public void massage() {
            System.out.println("Lucy massage()");
        }
    }

    // 每个代理类，只能为一个接口服务
    public static class Agent implements Massage {
        private final Massage massage;

        public Agent(Massage massage) {
            this.massage = massage;
        }

        public void before() {
            System.out.println("before");
        }

        public void after() {
            System.out.println("after");
        }

        @Override
        public void massage() {
            before();
            massage.massage();
            after();
        }
    }

    public static class Alvin implements Massage, Wash {

        @Override
        public void massage() {
            System.out.println("Alvin massage()");
        }

        @Override
        public void wash() {
            System.out.println("Alvin wash()");
        }
    }

}