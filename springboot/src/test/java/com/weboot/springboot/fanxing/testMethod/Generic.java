package com.weboot.springboot.fanxing.testMethod;

/*public class Generic {
    public void show(String s){
        System.out.println(s);
    }
    public void show(Integer i){
        System.out.println(i);
    }
    public void show(Boolean b){
        System.out.println(b);
    }
}*/

//泛型类改进
/*public class Generic<T>{
    public void show(T t){
        System.out.println(t);
    }
}*/


//泛型方法
public class Generic<T>{//这里的泛型可有可无，
    /*public <T> void show(T t){
        System.out.println(t);
    }*/
    public <T> void show(T t){
        System.out.println(t);
    }

}

//子类继承父类，如果泛型变量没有具体到类比如Student，而是用E , T ,V ,P表示，那么子类泛型必须包含父类泛型，可以拓展但不能少。
class MuGeneric<E,Y> extends Generic<Y>{

}