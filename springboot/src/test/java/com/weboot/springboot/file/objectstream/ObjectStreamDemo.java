package com.weboot.springboot.file.objectstream;

import java.io.*;

/**
 * 1.用对象序列化流序列化了一个对象后，假如我们修改了对象所属的类文件，读取数据会不会出问题呢？
 *      会，因为将对象序列化并写入文件或网络后，现在文件中存的是默认的序列化ID(非显示声明)，现在修改类，那么序列化ID会被更新，
 *      在反序列化时，会比较更改后的序列化ID和文件中的序列化ID，不一致，报InvilidClassException异常
 * 2.怎么解决？
 *      新增一个private static final long serialVersionUID = -3047464422819253885L;
 * 3.不想序列化成员变量
 *      private transient int age;//不参与序列化过程，序列化文件中没有此字段
 */
public class ObjectStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        write();
        read();

    }

    //序列化
    public static void write() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("ll.txt"));

        Student student = new Student("林青霞",30);
        objectOutputStream.writeObject(student);

        objectOutputStream.close();
    }

    public static void read() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("ll.txt"));

        Object obj = objectInputStream.readObject();

        Student student = (Student) obj;
        System.out.println(student);
        objectInputStream.close();
    }
}
