package com.weboot.springboot.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo {
    public static void main(String[] args) {
        Collection<Student> collection = new ArrayList<>();
        Student student = new Student("lin",29);
        Student student1 = new Student("lin1",22);
        Student student2 = new Student("lin2",25);
        collection.add(student);
        collection.add(student1);
        collection.add(student2);

        Iterator<Student> iterator = collection.iterator();
        while (iterator.hasNext()){
            Student student3 = iterator.next();
            System.out.println(student3);
        }
    }
}
