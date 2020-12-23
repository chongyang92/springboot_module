package com.weboot.springboot.object;
//封装
//1、成员变量私有化
//2、set方法中对传入参数校验(非空等正则验证)、或统一修改(相对于修改一百处 name = 30)；重点在于更深层次的定制
//3、模块化，
public class PackageAfterTest {
    public static void main(String[] args) {
        //婚前
        PackageMan man = new PackageMan("小明",20);
        PackageWomen women = new PackageWomen("小花",19);

        System.out.println("男婚前："+man);
        System.out.println("女婚前："+women);
        man.marry(women);
        System.out.println("男婚后："+man);
        System.out.println("女婚后："+women);

        //小偷来了
        PackageThief thief = new PackageThief("小坏",30);
        System.out.println("小偷，偷前："+thief);
        thief.thiefWife(man);
        System.out.println("小偷，偷后："+thief);

        System.out.println("小明，被偷后："+man);

    }
}

class PackageMan{
    private String name;
    private int age;
    private String marryInfo;
    private String wifeName;

    public PackageMan(String name, int age){
        this.name = name;
        this.age = age;
    }
    public void marry(PackageWomen women){
        this.marryInfo = name + "娶了" + women.getName();
        women.marry(this);
        wifeName = women.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMarryInfo() {
        return marryInfo;
    }

    public void setMarryInfo(String marryInfo) {
        this.marryInfo = marryInfo;
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
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

class PackageWomen{
    private String name;
    private int age;
    private String marryInfo;
    private String HasbandName;
    public PackageWomen(String name,int age){
        this.name = name;
        this.age = age;
    }
    public void marry(PackageMan men){
        this.marryInfo = name + "嫁给了" + men.getName();
        HasbandName = men.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMarryInfo() {
        return marryInfo;
    }

    public void setMarryInfo(String marryInfo) {
        this.marryInfo = marryInfo;
    }

    public String getHasbandName() {
        return HasbandName;
    }

    public void setHasbandName(String hasbandName) {
        HasbandName = hasbandName;
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

class PackageThief extends PackageMan{

    public PackageThief(String name, int age) {
        super(name, age);
    }

    public void thiefWife(PackageMan man){
        /*this.wifeName = man.wifeName;
        this.marryInfo = this.name + "娶了" + man.wifeName;
        man.wifeName = null;
        man.marryInfo = null;*/

        this.setWifeName(man.getWifeName());
        this.setMarryInfo(this.getName() + "娶了" + man.getWifeName());
        man.setWifeName(null);
        man.setMarryInfo(null);

    }
}