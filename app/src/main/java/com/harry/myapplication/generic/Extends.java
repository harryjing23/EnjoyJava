package com.harry.myapplication.generic;

/**
 * Created on 2021/6/24.
 *
 * @author harry
 */
public class Extends {

    public static void main(String[] args) {
        // 泛型类的继承
        Pair<Worker2> pair = new ExtendsPair<Worker2>();

        Pair<Worker1> personPair = new Pair<>();
        Pair<Worker2> employeePair = new Pair<>();
        Pair<Worker3> workerPair = new Pair<>();
        // 注意：虽然泛型参数是继承关系，但泛型类没有继承关系。泛型参数一定要相同
//        Pair<Worker2> pair = new Pair<Worker3>();

        // 使用通配符解决上面的问题
        Pair<? extends Worker2> p = workerPair;
        test(workerPair);

        // ?代表使用了通配符。泛型的声明(泛型类/接口、泛型方法)只能用字母，使用泛型时才可以使用通配符
        // extends为上界通配符，即? extends A，A是上界。要传入A和A的子类

        // 使用了? extends，则泛型类中的泛型只能返回上界类型。因为只能确定为上界
//        Worker3 data = p.getData();
        Worker2 data = p.getData();
        // 但是? extends不能setData???
//        p.setData(new Worker2());

        // super为下界通配符，即? super A，A是下界。要传入A和A的父类
        Pair<? super Worker2> pp = personPair;
        testSuper(personPair);

        // ? super的getData只能返回Object。因为不知道参数是哪个父类，索性Object
        Object ppData = pp.getData();
        pp.setData(new Worker2());
        // 但是? super的不能setData父类，只能传入A和A的子类。因为只有A和A的子类才能安全的转型为A
//        pp.setData(new Worker1());

        // ### extends可以安全的get访问数据，super可以安全的set写入数据
    }

    public static void testSuper(Pair<? super Worker2> pair) {
    }

    public static void test(Pair<? extends Worker2> pair) {
    }

    public static class Worker1 {
    }

    public static class Worker2 extends Worker1 {
    }

    public static class Worker3 extends Worker2 {
    }

    public static class Pair<T> {
        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    // 泛型类可以继承
    public static class ExtendsPair<T> extends Pair<T> {
    }
}
