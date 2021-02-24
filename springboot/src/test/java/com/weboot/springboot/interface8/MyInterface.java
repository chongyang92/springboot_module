package com.weboot.springboot.interface8;

public interface MyInterface {
    void show1();
    void show2();
    //void show3();//接口升级，新增了方法,其实现类中，必须新增该方法实现类
    //也可以使用新增一个子接口，但是会产生新的问题，就是每新增一个方法(升级后)，就会多出一个子接口，
    //这个体系会越来越庞大

    /*public */default void show3(){  //public 可以省略，这里是把之前默认的比如public abstract void show1(),换成public default void show1(),进而省略为，default void show1()
        System.out.println("show3");
    }

    /*静态方法*/
    public static void show(){//只能用接口名调用，public 可以省略，这里是把之前默认的比如public abstract void show(),换成public static void show()进而省略为，static void show()
        System.out.println("show");
    }
}
