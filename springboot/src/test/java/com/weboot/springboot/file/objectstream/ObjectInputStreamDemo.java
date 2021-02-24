package com.weboot.springboot.file.objectstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("ll.txt"));

        Object obj = objectInputStream.readObject();

        Student student = (Student) obj;
        System.out.println(student);
        objectInputStream.close();
    }
}
