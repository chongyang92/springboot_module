package com.weboot.springboot.object;

import java.util.Arrays;

//String 类用final修饰，不能被继承，其方法和成员变量默认也是final
public class StringTest {
    private static String str = "123";
    private static char[] arr = {'a','b','c'};
    public static void main(String[] args) {
        /**返回新的字符串,最原始的字符串并没有被改变*/
        /**substring()、concat()、replace()*/
        String aa = str.substring(1,2);//返回新的字符串
        System.out.println(aa);
        System.out.println("str:"+str);
        String newStr = str.substring(0,str.length());//如果是从0到length，返回本身,;
        System.out.println(newStr == str);
        System.out.println("str:"+str);
        System.out.println(str.concat("aa"));//返回新的字符串
        System.out.println("str:"+str);
        char[] buf = Arrays.copyOf(arr,20);
        System.out.println(Arrays.toString(buf));
        System.out.println("str:"+str);
        System.out.println(str.replace("23","32"));
        System.out.println("str:"+str);

        String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = d + 2;//就看+的时候，是变量还是常量，d是变量，b是常量
        System.out.println("a == c:"+(a == c));
        System.out.println("a == e:"+(a == e));

        /*用 " " 声明的String，首先会检查String常量池中有没有，没有的话就会在常量池新建一个，
         * 然后返回地址，对于e是d和2而成，但由于d是变量，所以在编译期是不知道的，简单地说就是对于变量+常量的，
         * 相当于在运行期调用new，这样返回的就是堆里新建的地址，所以和a不一样，但是对于final修饰的b来说，
         * 值已经是确定的了，编译期已经知道，所以相当于""+""，这种情况下，返回的就是常量池里的引用。
         */

        new StringTest().test14();
    }

    /**
     * 采用字面值的方式赋值
     */
    public void test1(){
        String str1="aaa";
        String str2="aaa";
        System.out.println("===========test1============");
        System.out.println(str1==str2);//true 可以看出str1跟str2是指向同一个对象
    }

    /**
     * 采用new关键字新建一个字符串对象
     */
    public void test2(){
        String str3=new String("aaa");
        String str4=new String("aaa");
        System.out.println("===========test2============");
        System.out.println(str3==str4);//false 可以看出用new的方式是生成不同的对象
    }

    /**
     * 编译期确定
     */
    public void test3(){
        String s0="helloworld";
        String s1="helloworld";
        String s2="hello"+"world";//+的时候，都是字符串常量，编译期确定了
        System.out.println("===========test3============");
        System.out.println(s0==s1); //true 可以看出s0跟s1是指向同一个对象
        System.out.println(s0==s2); //true 可以看出s0跟s2是指向同一个对象
    }

    /**
     * 编译期无法确定
     */
    public void test4(){
        String s0="helloworld";
        String s1=new String("helloworld");
        String s2="hello" + new String("world");
        System.out.println("===========test4============");
        System.out.println( s0==s1 ); //false
        System.out.println( s0==s2 ); //false
        System.out.println( s1==s2 ); //false
    }

    /**
     * 继续-编译期无法确定
     */
    public void test5(){
        String str1="abc";
        String str2="def";
        String str3=str1+str2;
        System.out.println("===========test5============");
        System.out.println(str3=="abcdef"); //false
    }
    /**分析：因为str3指向堆中的"abcdef"对象，而"abcdef"是字符串池中的对象，所以结果为false。JVM对String str="abc"对象放在常量池中是在编译时做的，而String str3=str1+str2是在运行时刻才能知道的。new对象也是在运行时才做的。而这段代码总共创建了5个对象，字符串池中两个、堆中三个。+运算符会在堆中建立来两个String对象，这两个对象的值分别是"abc"和"def"，也就是说从字符串池中复制这两个值，然后在堆中创建两个对象，然后再建立对象str3,然后将"abcdef"的堆地址赋给str3。
     步骤：
     1)栈中开辟一块中间存放引用str1，str1指向池中String常量"abc"。
     2)栈中开辟一块中间存放引用str2，str2指向池中String常量"def"。
     3)栈中开辟一块中间存放引用str3。
     4)str1 + str2通过StringBuilder的最后一步toString()方法还原一个新的String对象"abcdef"，因此堆中开辟一块空间存放此对象。
     5)引用str3指向堆中(str1 + str2)所还原的新String对象。
     6)str3指向的对象在堆中，而常量"abcdef"在池中，输出为false。*/

    /**
     * 编译期优化
     */
    public void test6(){
        String s0 = "a1";
        String s1 = "a" + 1;
        System.out.println("===========test6============");
        System.out.println((s0 == s1)); //result = true
        String s2 = "atrue";
        String s3= "a" + "true";
        System.out.println((s2 == s3)); //result = true
        String s4 = "a3.4";
        String s5 = "a" + 3.4;
        System.out.println((s4 == s5)); //result = true
    }

    /**
     * 编译期无法确定
     */
    public void test7(){
        String s0 = "ab";
        String s1 = "b";
        String s2 = "a" + s1;
        System.out.println("===========test7============");
        System.out.println((s0 == s2)); //result = false
    }

    /**
     * 比较字符串常量的“+”和字符串引用的“+”的区别
     */
    public void test8(){
        String test="javalanguagespecification";
        String str="java";
        String str1="language";
        String str2="specification";
        System.out.println("===========test8============");
        System.out.println(test == "java" + "language" + "specification");
        System.out.println(test == str + str1 + str2);
    }

    /**
     * 编译期确定
     */
    public void test9(){
        String s0 = "ab";
        final String s1 = "b";
        String s2 = "a" + s1;
        System.out.println("===========test9============");
        System.out.println((s0 == s2)); //result = true
    }

    /**
     * 编译期无法确定
     */
    public void test10(){
        String s0 = "ab";
        final String s1 = getS1();
        String s2 = "a" + s1;
        System.out.println("===========test10============");
        System.out.println((s0 == s2)); //result = false

    }

    private static String getS1() {
        return "b";
    }

    /**
     * 关于String.intern()
     */
    public void test11(){
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = new String("kvill");
        System.out.println("===========test11============");
        System.out.println( s0 == s1 ); //false
        System.out.println( "**********" );
        s1.intern(); //虽然执行了s1.intern(),但它的返回值没有赋给s1
        s2 = s2.intern(); //把常量池中"kvill"的引用赋给s2
        System.out.println( s0 == s1); //flase
        System.out.println( s0 == s1.intern() ); //true//说明s1.intern()返回的是常量池中"kvill"的引用
        System.out.println( s0 == s2 ); //true
    }
    /**一个初始为空的字符串池，它由类String独自维护。当调用 intern方法时，
     * 如果池已经包含一个等于此String对象的字符串（用equals(oject)方法确定），
     * 则返回池中的字符串。否则，将此String对象添加到池中，并返回此String对象的引用。*/

    /**
     * 关于equals和==
     */
    public void test12(){
        String s1="hello";
        String s2="hello";
        String s3=new String("hello");
        System.out.println("===========test12============");
        System.out.println( s1 == s2); //true,表示s1和s2指向同一对象，它们都指向常量池中的"hello"对象
        //flase,表示s1和s3的地址不同，即它们分别指向的是不同的对象,s1指向常量池中的地址，s3指向堆中的地址
        System.out.println( s1 == s3);
        System.out.println( s1.equals(s3)); //true,表示s1和s3所指向对象的内容相同
    }

    /**
     * String相关的+
     */
    public void test13(){
        String a = "aa";
        String b = "bb";
        String c = "xx" + "yy " + a + "zz" + "mm" + b;
        System.out.println("===========test13============");
        System.out.println(c);
    }

    /**
     * String相关的new String("ee")创建多少对象
     */
    public void test14(){
        String a = new String("ee");
        //"ee".intern();
        System.out.println("===========test14============");
        System.out.println(a);
        //System.out.println("ee".intern());
    }
}
