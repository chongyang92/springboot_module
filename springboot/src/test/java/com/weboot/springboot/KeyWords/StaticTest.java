package com.weboot.springboot.KeyWords;


import com.weboot.springboot.datatype.Variables_valueOrReference;

//1.只能修饰成员变量,将其变为类的成员，从而实现所有对象对于该成员的共享；
//2.修饰成员方法,将其变为类方法，可以直接使用“类名.方法名”的方式调用，常用于工具类；
//  不能直接引用成员变量，需要先new,比如main
//3.静态代码块（和静态变量一样）,将多个类成员放在一起初始化，使得程序更加规整，其中理解对象的初始化过程非常关键；
    //3.0声明顺序：静态代码块和静态变量同时被声明，但未被初始化
    //3.1赋值(初始化)顺序：静态代码块和静态变量(就是把所有static标识的变量放在一块，如果其中一个有调用静态方法，按照先后书写顺序，先调用静态方法，再对其他静态变量赋值)—>成员变量声明和赋值->构造方法
    //3.2如果有父类，按照父类静态代码块和静态变量(访问不到修饰符为private的),先加载父类的，然后加载子类的，父类的赋值，然后子类的赋值->成员变量声明和赋值->构造方法
//4.修饰内部类
//5.导入静态代码包,将类的方法直接导入到当前类中，从而直接使用“方法名”即可调用类方法，更加方便。
import static java.lang.Math.abs;
public class StaticTest {

    //修饰成员方法
    static void foo(){
        name = "101";
        System.out.println("789");
    }

    static {
        //private int a = 5;//这个属于局部变量，不能使用访问控制符
        int b = 6;
        name = "iii";
        System.out.println("static0");

    }

    //修饰成员变量变成类变量
    private static String name = "123";
    //修饰成员变量变成类变量
    public static String age = "456";
    private static String fooVar = foo1();
    //成员变量
    private String address = "1011";

    static String foo1(){
        //address = "000";//不能直接引用，需要先new出对象来
        //对于new出来的没有意义
        //new StaticTest().address = "2022";
        name = "654";//静态方法可以引用静态变量
        System.out.println("static1");
        return "1";
    }



    private StaticTest(){
        System.out.println("strute");
    }


    public static void main(String[] args) {
        /**只用来修饰成员变量，不修饰局部变量*/
        /*private String tt = "4";
        static String ff = "4";*/

        StaticTest staticTest = new StaticTest();

        //static int aa = 6;//不能把局部变量定义成类变量。
        //类变量使用类名调用
        System.out.println(StaticTest.name);
        //类变量使用对象调用
        System.out.println(staticTest.name);//不推荐
        //类变量使用对象调用
        System.out.println(staticTest.age);//不推荐
        //静态方法使用类名调用
        StaticTest.foo();
        System.out.println("address before:"+staticTest.address);
        StaticTest.foo1();
        System.out.println("address after:"+staticTest.address);
        //System.out.println(StaticTest.fooVar);

        /**调用到本类之外的类变量，只看有没有被开放*/
        C c = new C();
        System.out.println(c.cc);
        C.foo();
        c.foo();
        //c.foo1();//不能访问private

        Variables_valueOrReference valueOrReference = new Variables_valueOrReference();
        //System.out.println(valueOrReference.protectedValibale);//不同包，无继承关系

    }
}
class C extends Variables_valueOrReference{
    public C(){
        System.out.println(cc);
        System.out.println("C structure");
    }
    static String cc = "ccccc";
    //修饰成员方法
    static void foo(){
        System.out.println(Variables_valueOrReference.protectedValibale);
    }
    private static void foo1(){
        System.out.println(Variables_valueOrReference.protectedValibale);
    }

    public static void main(String[] args) {
        new C();
        //C.foo();
    }

}
