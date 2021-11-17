package com.harry.myapplication.kotlin

/**
 * Created on 2021/10/27.
 * @author harry
 */
class Test {


    // Kotlin也是编译成.class文件，再给jvm执行
    // 学习Kotlin要用Tools->Kotlin->Show Kotlin Bytecode，反编译成java文件来分析

    // 所有数据类型在Kotlin中被又一次封装，添加了kotlin方法。基本数据类型全变成封装类
    fun main() {
        // 不可修改的变量。加了final。只有get
        val name: String = "Derry"
        // 可修改的变量。有get和set
        var name1: String = "derry"
        // 类型自动推导
        val info = "info"

        lengthMethod(1, 2, 3, 4)

        // lambda表达式函数，相当于下面的add函数。->前是参数，->后是返回值
        val addMethod: (Int, Int) -> Int = { num1, num2 -> num1 + num2 }
        val value = addMethod(9, 8)

        // 字符串模板。用$引用变量的值
        println("name: $name, info: $info")

        // 三引号，可以保留字符串的格式和换行。用trimIndent()可以去掉多余的缩进和空格
        val msg: String = """
            aaaa
            bbbb
            cccc
        """.trimIndent()
        println(msg)


        // 用trimMargin()可以去掉边界字符以及前面的内容
        val msg1: String = """
            |aaaa
            |bbbb
            |cccc
        """.trimMargin("|")

        // 字符串中。$a表示变量的值；${a+b}是串模板，会计算里面的表达式。想用字符$，通过${'$'}
        val price: String = "${'$'}99.9"// 显示$99.9


        // 所有变量不能初始化为空。变量类型的后面加?，才能初始化为空，但使用时必须给出补救方案（抛给了调用者）
        var info1: String? = null
//        println(info1.length)// 直接使用会报错
        println(info1?.length)// 补救方式一：变量后加?，表示为空则不执行?后面。相当于Java判空后不为空再执行
        println(info1!!.length)// 补救方式二：变量后加!!，只避免了语法检查，为空则报空指针异常。相当于Java没有判空

        println(info1?.length ?: "牛逼")// ?: 代表前面不为空则取前面，为空则取后面
    }

    // 函数没有返回值时，可省略。编译器会隐式返回Unit类型，类似于void
    fun add(num1: Int, num2: Int): Int {

        return num1 + num2
    }

    // 函数返回值的类型推导
    fun add2(num1: Int, num2: Int) = num1 + num2


    // 可变长参数vararg
    fun lengthMethod(vararg value: Int) {
        // 使用方法类似于数组。也可以用区间遍历
        for (i in value) {

        }
    }

    // Kotlin称为函数，而不是方法。是因为方法只能写在类里面


    // 函数默认不允许return null。函数的返回值类型后加?，可以使函数返回空
    fun test(): Int? {
        return null
    }


    // 区间
    fun qujian() {
        // 区间 递增 in ..
        for (i in 1..9) {// in是闭区间
            println(i)
        }

        // 区间 递减 in downTo
        for (j in 9 downTo 1) {
            println(j)
        }


        // 区间除了遍历用，还可以作判断条件
        val value = 88
        if (value in 1..99) {
            println("true")
        }

        // step指定区间的步长
        for (k in 1..99 step 2) {
            println(k)
        }

        // until是半闭半开区间
        for (l in 1 until 10) {
            println(l)
        }


    }

    // 比较
    fun equal() {
        val name1 = "Tom"
        val name2 = "Tom"

        println(name1.equals(name2))
        // 与Java的equals相同，不再是比较对象的地址
        println(name1 == name2)
        // Kotlin中比较对象的地址用===
        println(name1 === name2)
        // ps: java基本数据类型用==比较的是值
    }

    // 数组
    fun array() {
        // 创建数组的第一种形式：

        // Kotlin的数组要用Array类
        val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)

        println(numbers[3])

        // 用区间遍历数组
        for (number in numbers) {
            println(number)
        }

        // 创建数组的第二种形式：
        // 参数1：数组长度。参数2：数组元素的变量声明和初始化
        // 从200开始依次+1，Int默认+1。即200,201,202,203,204,205
        val numbers2: Array<Int> = Array(6, { value: Int -> (value + 200) })
        for (v in numbers2) {
            println(v)
        }

    }

    // 条件控制
    fun condition() {
        val number1 = 1
        val number2 = 2

        // if语句是表达式，表达式是有返回值的。返回最后一条语句
        val result = if (number1 > number2) {
            println("1大")
            number1// 返回值
        } else {
            println("2大")
            number2// 返回值
        }

        // Kotlin用when代替Java的switch
        val number = 3
        val w = when (number) {
            // 也可以用区间做条件
            in 100..200 -> {
                println("1")
                100
            }
            2 -> {
                println("2")
                2
            }
            // 多条件用,隔开
            3, 4, 5 -> 3
            else -> {
                println("else")
                -1
            }
        }

        // 若when的不同分支的返回值类型不同，自动类型推导会推导出Any类型，相当于Java的Object


    }

    // 循环
    fun loop() {

        tttt@ for (i in 1..10) {
            for (j in 1..10) {
                if (j == 5) {
                    println("i:$i j:$j")
                    // break标签位置的外循环。不加标签只是break内循环
                    break@tttt
                }
            }
        }
    }

    class Derry {
        val i = "a"

        // 以下三个打印都相同
        fun show() {
            println(i)
            println(this.i)
            // 类也会自带标签
            println(this@Derry.i)
        }
    }

    // 循环
    fun foreach() {
        val items = listOf<String>("张三", "李四")
        // 用区间遍历集合
        for (item in items) {
            println(item)
        }
        // 用for each遍历集合
        // 集合元素的默认变量名为it
        items.forEach {
            println(it)
        }
        // 修改it变量名
        items.forEach { aaa ->
            println(aaa)
        }

        // 用集合的indices变量，可以取到下标（indices是index的复数形式）
        for (index in items.indices) {
            // items[index]是表达式，需要用{}括起来
            println("index: $index, value: ${items[index]}")
        }
    }


    // 类与对象
    // Kotlin类默认为public final
    // 类可以没有{}
    class Empty

    // Kotlin构造函数分为主构造和此构造。类名后面是主构造，只能有一个
    // open代表不是final，可以被继承
    open class Person(id: Int) {
        // constructor是次构造，必须用:调用主构造。:代表继承或实现
        constructor(id: Int, name: String) : this(id) {
        }

        // 次构造的重载
        constructor(id: Int, sex: Char) : this(id) {
        }

        // 构造函数可以没有{}
        constructor() : this(0)
    }

    fun clazz() {
        // 创建对象不用new
        val person = Person()

    }

    // 父类有构造函数时，声明继承关系时必须要调用
    // 后面的父类可以直接用前面子类的参数
    class Student(id: Int) : Person(id) {
        // Java中成员有默认值，只有方法中的变量没有默认值
        // Kotlin中所有变量都没有默认值，需要指定
        var age: Int = 0

        // 可以用懒加载lateinit不初始化默认值。因为若初始化为null，还要加?，麻烦
        lateinit var name: String
    }

    // 接口默认是open的，以便实现
    interface Callback {
        fun callbackMethod(): Boolean
    }

    // 抽象类默认是open的
    abstract class PersonImpl : Callback {
        abstract fun getLayoutId(): Int

    }

    // 因为类有默认的无参主构造函数，所以继承的类要加()
    class StudentImpl : PersonImpl() {
        // 函数表达式。没有函数体的函数
        override fun getLayoutId(): Int = 8

        override fun callbackMethod(): Boolean = false

    }

    // 数据类代替Java Bean，更简洁。自动生成了get/set、构造函数、toString/hashcode/equals、copy
    data class User(val id: Int, val name: String, val sex: Char)

    fun t() {
        val user = User(1, "Tom", 'F')

        // 可以直接取出数据类的属性并赋给新的变量
        val (myId, myName, mySex) = user.copy()

        // 用_表示拒收第几个属性
        val (_, myName1, _) = user.copy()
    }

    // object代表单例类，是没有构造函数的。用普通的饿汉式实现，内部用静态的INSTANCE持有实例
    object MyEngine {

        fun m() {
            println("m")
        }
    }

    fun a() {
        // object创建对象时不用加()，所以没有主构造，更没有次构造。
        // 使用起来类似Java的静态，但想让他真正的静态，要在成员上加@JvmStatic
        MyEngine.m()
    }


    // 写一个延迟初始化的饿汉式单例
    class NetManager {

        object Holder {
            val instance = NetManager()
        }

        // 派生类companion object，跟着外部类一起出生。里面的都相当于Java的static
        companion object {
            fun getInstance(): NetManager = Holder.instance
        }

        fun show() {
            println("NetManager")
        }
    }

    fun b() {
        // 创建单例类的对象
        val net = NetManager.getInstance()
        net.show()
    }


    class Test {
        val i = "aaa"

        // 不像Java。这不是个内部类，故拿不到外部类的成员。只是个嵌套类
        class Sub {
            fun show() {
                // 拿不到i，报错
//                println(i)
            }
        }

        // inner class才是内部类
        inner class Sub2 {
            fun show() {
                println(i)
            }
        }
    }
}