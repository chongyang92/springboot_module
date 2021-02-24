package com.weboot.springboot.file.objectstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("ll.txt"));

        Student student = new Student("林青霞",30);
        objectOutputStream.writeObject(student);

        objectOutputStream.close();
    }
}
