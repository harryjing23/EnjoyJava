package com.harry.myapplication.generic;

/**
 * Created on 2021/6/21.
 *
 * @author harry
 */
// 泛型类的声明
public class NormalGeneric<T, K> {
    private T data;
    public K result;
    // 静态成员（变量和方法）中不能引用泛型类型，因为只有在创建类的实例时才会知道泛型类型
    // 但是泛型方法可以是静态的
//    public static T st;

    // 该方法只是普通方法。只有带<>泛型声明的方法，才是泛型方法
    // 在泛型类或泛型接口中，才可以使用其声明的泛型。eg.该方法的参数
    public void test(NormalGeneric<T, K> generic) {

    }

    // 该方法也是普通方法。
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 泛型类中，泛型方法的泛型与该类的泛型不是同一个，故可以指定不同的泛型类型。泛型类的泛型不影响泛型方法。
    public <T> void test(T t) {
        // 泛型不能直接实例化
//        T t1 = new T();
    }

    public static void main(String[] args) {
        NormalGeneric<String, String> generic = new NormalGeneric<>();
        generic.setData("OK");
        System.out.println(generic.getData());

        // 泛型类不能使用instanceof运算符
//        boolean b = generic instanceof NormalGeneric<String,String>();

        // 泛型类的getClass是泛型类的原始类型 eg.NormalGeneric
        System.out.println(generic.getClass());

        // 不可以实例化泛型数组，但可以声明
        NormalGeneric<Integer, String>[] array;
//        array = new NormalGeneric<Integer, String>[10];
    }

    // 泛型类不能extends Exception或Throwable
//    class Problem<T> extends Exception {
//    }

    // 泛型限定为Exception或Throwable时，即T extends Exception/Throwable。泛型不能被catch，但可以throws
}
