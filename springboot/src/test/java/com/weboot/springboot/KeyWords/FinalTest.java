package com.weboot.springboot.KeyWords;

/**final关键字可以用来修饰类、方法和变量（包括成员变量和局部变量），只能被赋值一次*/
//修饰成员变量:常量，在定义时初始化，或者在构造方法中初始化
//修饰局部变量：局部常量，一般是方法的参数final，不能被改变
//修饰类变量（static）:类常量，定义在方法外，在多个方法中被使用；必须在定义时初始；
//修饰成员方法(不能修饰静态方法)：方法不能被重写
//修饰类：不能被继承String、Integer等，final类中的成员变量可以根据需要设为final，但是要注意final类中的所有成员方法都会被隐式地指定为final方法
public class FinalTest{
    private static final long serialVersionUID = 8407793182956610280L;
    //修饰成员变量
    final double PI = 3.14;
    final String name = "字符串";
    final Integer id = 5;
    final StringBuffer stringBuffer = new StringBuffer("字符串new");
    static final  String fs = "KKK";//静态常量
    final short sid = 4;
    final short aa ;//如果此时不初始化，在构造方法中初始化；

    //在构造方法中初始化常量
    //
    //private FinalTest() {
    FinalTest() {
        //aa = sid + id;//sid+id会出现向上转型，2个字节的short,和4个字节的Integer会产出结果为4个字节的Integer,
        aa = sid +5;
    }



    //修饰局部变量和参数变量
    void foo(final String str){
        final int a;
        //str = "666";//不能被改变
        System.out.println(str);
    }
    //修饰方法，防在返回类型前
    final void finalFoo(){
        System.out.println("finalFoo");
    }

    //不能修饰静态方法
    /*static final  finalStaticFoo(){

    }*/
    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        System.out.println(finalTest.PI);
        finalTest.foo("修饰局部变量和参数变量");
        finalTest.finalFoo();
        System.out.println("aa:"+finalTest.aa);

        String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));

        /*用 " " 声明的String，首先会检查String常量池中有没有，没有的话就会在常量池新建一个，
         * 然后返回地址，对于e是d和2而成，但由于d是变量，所以在编译期是不知道的，简单地说就是对于变量+常量的，
         * 相当于在运行期调用new，这样返回的就是堆里新建的地址，所以和a不一样，但是对于final修饰的b来说，
         * 值已经是确定的了，编译期已经知道，所以相当于""+""，这种情况下，返回的就是常量池里的引用。
         */

        Integer aa = 121;
        final Integer bb = 119;
        final Integer ff = 121;
        int dd = 119;
        Integer cc = bb + 2;
        Integer ee = dd + 2;
        System.out.println("aa==cc:"+(aa == cc));
        System.out.println("aa==bb:"+(aa == ff));
        System.out.println("aa==ee:"+(aa == ee));
        /* 8种基本类型的包装类和对象池
           java中基本类型的包装类的大部分都实现了常量池技术，这些类是Byte,Short,Integer,Long,Character,Boolean,
           另外两种浮点数类型的包装类则没有实现。
           另外Byte,Short,Integer,Long,Character这5种整型的包装类也只是在对应值小于等于127时才可使用对象池，
           也即对象不负责创建和管理大于127的这些类的对象。*/

    }
}

class FinalChild extends FinalTest{
    /*@Override
    void finalFoo(){

    }*/ //不能被重写
    public static void main(String[] args) {
        FinalChild finalChild = new FinalChild();
        System.out.println(finalChild.PI);
        //finalZi.PI = 5; //继承的子类不能修改成员变量
        System.out.println(finalChild.name);
        //finalZi.name = "新字符串";//String类型的变量也不能被改变，这里是把name换了一个新的值
        System.out.println(finalChild.id);
        //finalZi.id = 6;//Integer、Short、Long、Character这种包装类都不能被改变
        finalChild.finalFoo();//可以调用
        finalChild.stringBuffer.append("final修饰的成员变量,其指向的内存,可以被改掉");
        System.out.println(finalChild.stringBuffer);
        //finalChild.stringBuffer = new StringBuffer("666");//final修饰的成员变量,其指向,不能修改
    }
}
