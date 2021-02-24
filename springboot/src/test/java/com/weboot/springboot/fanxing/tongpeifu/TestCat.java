package com.weboot.springboot.fanxing.tongpeifu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class TestCat {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();

        //cats.addAll(animals);

        //showAnimalUp(animals);
        showAnimalUp(cats);
        showAnimalUp(miniCats);

        showAnimalDown(animals);
        showAnimalDown(cats);
        //showAnimalDown(miniCats);
        System.out.println("--------TreeSet---------");

        TreeSet<Cat> treeSet = new TreeSet<>(new Comparator2());
        treeSet.add(new Cat("jerry",20));
        treeSet.add(new Cat("any",22));
        treeSet.add(new Cat("frank",35));
        treeSet.add(new Cat("jim",15));

        for(Cat cat : treeSet){
            System.out.println(cat);
        }
    }


    //泛型上限通配符，传递集合类型，或子类型
    public static void showAnimalUp(ArrayList<? extends Cat> list){
        for (int i = 0; i < list.size(); i++) {
            Cat cat = list.get(i);
            System.out.println(cat);
        }
    }
    //只能要求是Cat或Cat的父类
    public static void showAnimalDown(ArrayList<? super Cat> list){
        /*list.add(new Cat());
        list.add(new MiniCat());*/
        //list.add(new Animal());
        for(Object o : list){
            System.out.println(o);
        }
    }
}

class Comparator1 implements Comparator<Animal>{

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.name.compareTo(o2.name);
    }
}

class Comparator2 implements Comparator<Cat>{

    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.age - o2.age;
    }
}

class Comparator3 implements Comparator<MiniCat>{

    @Override
    public int compare(MiniCat o1, MiniCat o2) {
        return o1.level - o2.level;
    }
}