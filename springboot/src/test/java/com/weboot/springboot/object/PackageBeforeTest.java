package com.weboot.springboot.object;

//封装
public class PackageBeforeTest {

    public static void main(String[] args) {
        //婚前
        Man man = new Man("小明",20);
        Women women = new Women("小花",19);

        System.out.println("男婚前："+man);
        System.out.println("女婚前："+women);
        man.marry(women);
        System.out.println("男婚后："+man);
        System.out.println("女婚后："+women);
        //小偷来了
        Thief thief = new Thief("小坏",30);
        System.out.println("小偷，偷前："+thief);
        thief.thiefWife(man);
        System.out.println("小偷，偷后："+thief);

        System.out.println("小明，被偷后："+man);
    }


}

class Man{
    public String name;
    public int age;
    public String marryInfo;
    public String wifeName;
    public Man(String name,int age){
        this.name = name;
        this.age = age;
    }
    public void marry(Women women){
        this.marryInfo = name + "娶了" + women.name;
        women.marry(this);
        wifeName = women.name;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", marryInfo='" + marryInfo + '\'' +
                ", wifeName='" + wifeName + '\'' +
                '}';
    }
}

class Women{
    public String name;
    public int age;
    public String marryInfo;
    public String HasbandName;
    public Women(String name,int age){
        this.name = name;
        this.age = age;
    }
    public void marry(Man men){
        this.marryInfo = name + "嫁给了" + men.name;
        HasbandName = men.name;
    }

    @Override
    public String toString() {
        return "Women{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", marryInfo='" + marryInfo + '\'' +
                ", HasbandName='" + HasbandName + '\'' +
                '}';
    }
}

class Thief extends Man{

    public Thief(String name, int age) {
        super(name, age);
    }

    public void thiefWife(Man man){
        this.wifeName = man.wifeName;
        this.marryInfo = this.name + "娶了" + man.wifeName;
        man.wifeName = null;
        man.marryInfo = null;
    }

    @Override
    public String toString() {
        return "Thief{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", marryInfo='" + marryInfo + '\'' +
                ", wifeName='" + wifeName + '\'' +
                '}';
    }
}