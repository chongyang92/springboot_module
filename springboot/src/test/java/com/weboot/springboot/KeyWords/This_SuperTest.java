package com.weboot.springboot.KeyWords;

//this 代表当前对象
//this.变量/super.变量：存在同名成员变量与局部变量,哪个对象调用了this所在的函数，那么this就指代哪个对象；
//this()/super()构造方法：B继承A，在子类B的构造方法中this或super必须写在第一行，不写，编译器默认加上，并且是无参的；
//
class A{
    public A(){
        System.out.println("A 默认结构体");
    }
    public A(String str){
        System.out.println("A 有参构造方法："+str);
    }
    String s = "A";
    String a = "A";
    void print(){
        System.out.println("A:"+this.s);//结果 A
        System.out.println("A this:"+this.test());//结果test()被子类覆盖了
        System.out.println("AAAAAAAAAAAAAAAA");
    }
    public String test(){
        return "test_A";
    }
    protected String testStr(String str){
        return "test_AStr";
    }
    protected String testStr(Integer str){
        return "test_AStr";
    }
}
class B extends A{
    protected String testStr(String str){
        return "test_AStr";
    }
    public B(String str){
        this();
        //super();//此处如果不写，默认为super();，所以父类A中必须要有无参构造方法
        //super("A structure");//如果父类A中没有无参构造方法A(),则必须调用其有参构造方法

        System.out.println("B structure");
        //super();//报错，必须在构造方法第一行
    }
    public B(){
        super("111");
        String s = super.s;
        System.out.println("B() 无参构造方法");
    }
    private String s = "B";
    void print(){
        /*System.out.println("B:"+this.s);
        System.out.println("B this:"+this.test());*/
        super.print();
        System.out.println("super.test():"+super.test());
    }
    public String test(){
        return "test_B";
    }

    public String testB(){
        return "test_BB";
    }

    public static void main(String[] args) {
        B b = new B("222");
        System.out.println(b.a);//直接继承父类的成员变量
        System.out.println("-----------------");
        new B().print();
        A a = new B();
        System.out.println("a.test():"+a.test());
    }
}
//代表当前对象
public class This_SuperTest {
    public This_SuperTest(){
        name = "成员变量";
    }

    //成员变量
    private String name;

    //就近原则，局部变量和成员变量同名时，使用this区分出成员变量
    void foo(String name){
        System.out.println(name);
        System.out.println(this.name);
    }
    //不同名时，加不加this都指成员变量
    void fooTmp(String tmpName){
        System.out.println(tmpName);
        System.out.println(name);
        System.out.println(this.name);
    }

    public static void main(String[] args) {
        This_SuperTest thisTest = new This_SuperTest();
        thisTest.foo("方法中成员变量");
        thisTest.fooTmp("方法中成员变量Tmp");
        A a = new B();
        a.print();
        B b = new B();
        b.print();
    }
}
