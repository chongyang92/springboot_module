package com.weboot.springboot.datatype;

//1.共有三种变量，类变量(所有对象共享)，成员变量，局部变量，分别存在jvm的方法区（共享数据区）的静态区、堆、栈
/*2.传值还是传地址:
    2.1基本类型作为参数传递时，是传递值的拷贝，无论你怎么改变这个拷贝，原值是不会改变的
*   2.2引用数据类型(包括对象、数组、接口)作为参数传递时，是把对象在内存中的地址拷贝了一份传给了参数；（例如StringBuffer、StringBuilder，是否指向了新申请的内存）
    2.3基本数据类型的封装类包括String、Integer、Short、Float、Double、Long、Boolean、Byte、Character虽然是引用类型，但它们在作为参数传递时，也和基本数据类型一样，是值传递
 */
public class Variables_valueOrReference {

    //构造方法需要为public方便别的包创建对象
    public Variables_valueOrReference(){
        System.out.println("Variables_valueOrReference structure");
    }

    //类变量——也叫静态变量；
    //独立于方法之外的变量，用static修饰；
    //用类名调用Variables.classVariable
    public static String classVariable = "466";
    private static Integer baseClassValibaleInteger = 000;
    private static int baseClassValibale;
    //
    protected static int protectedValibale;

    //成员变量——也叫实例变量
    //独立于方法之外的变量，没有static修饰
    //想new出对象再调用
    private String name = "Variables name";
    private Integer ageInteger;
    private int age;

    //局部变量——age
    //方法之内的变量
    private void getAge(int c){
        int d;
    }
    private int getD(int e){
        return e;
    }

    //传值还是传地址，此处传对象的地址，改掉了age的值，结果为44
    private Variables_valueOrReference setAge(Variables_valueOrReference variablesValueOrReferenceAge){
        variablesValueOrReferenceAge.age = 44;
        return variablesValueOrReferenceAge;
    }

    //传的是值
    private void setAge(int variablesAge){
        variablesAge = 444;
    }

    //传的是值
    private void setAgeInteger(Integer ageInteger){
        ageInteger = 4444;
    }

    void foo(String str){
        str = "str";
    }

    //这里的concat方法会new一个新的String对象
    private void fooConcat(String str){
        str.concat("ooooooooooo");
    }
    private void fooStringBuffer(StringBuffer str){
        str.append("str");
    }

    private void fooStringBufferNew(StringBuffer str){
        str = new StringBuffer("789");
        str.append("str");
    }

    private StringBuffer fooStringBufferNewReturn(StringBuffer str){
        str = new StringBuffer("1011");
        str.append("str");
        return str;
    }

    public static void main(String[] args) {
        System.out.println("classVariable:"+classVariable);
        System.out.println("Variables.classVariable:"+ Variables_valueOrReference.classVariable);
        System.out.println(new Variables_valueOrReference().age);
        new Variables_valueOrReference().getAge(3);
        System.out.println(new Variables_valueOrReference().getD(4));

        //传值还是传地址——传地址
        Variables_valueOrReference variablesValueOrReference = new Variables_valueOrReference();
        variablesValueOrReference.age = 4;
        System.out.println("before age:"+ variablesValueOrReference.age);
        variablesValueOrReference.setAge(variablesValueOrReference);
        System.out.println("after age 传地址:"+ variablesValueOrReference.age);
        int ageV = 5;
        variablesValueOrReference.setAge(ageV);
        System.out.println("ageV 传值:"+ageV);
        int ageInteger = 6;
        variablesValueOrReference.setAgeInteger(ageInteger);
        System.out.println("ageInteger 传值:"+ageInteger);
        Integer integer = new Integer(7);
        variablesValueOrReference.setAgeInteger(integer);
        System.out.println("integer 传对象:"+integer);

        String string = "string";
        variablesValueOrReference.foo(string);
        System.out.println("字符串传值还是传地址："+string);

        String stringConcat = new String("stringConcat");
        variablesValueOrReference.fooConcat(stringConcat);
        System.out.println("字符串传值还是传地址stringConcat："+stringConcat);

        StringBuffer stringBuffer = new StringBuffer("123");
        variablesValueOrReference.fooStringBuffer(stringBuffer);
        System.out.println("stringBuffer:"+stringBuffer);

        //重点,stringBufferNew和str都指向了同一块内存456，方法中str指向变了，但是stringBufferNew指向没有变；
        StringBuffer stringBufferNew = new StringBuffer("456");
        variablesValueOrReference.fooStringBufferNew(stringBufferNew);
        System.out.println("stringBufferNew:"+stringBufferNew);

        //重点,fooStringBufferNewReturn和str都指向了同一块内存4567，方法中str指向变了，但是stringBufferNew指向没有变；
        StringBuffer fooStringBufferNewReturn = new StringBuffer("4567");
        variablesValueOrReference.fooStringBufferNewReturn(fooStringBufferNewReturn);
        System.out.println("fooStringBufferNewReturn before:"+fooStringBufferNewReturn);
        fooStringBufferNewReturn = variablesValueOrReference.fooStringBufferNewReturn(fooStringBufferNewReturn);
        System.out.println("fooStringBufferNewReturn after:"+fooStringBufferNewReturn);
    }
}
