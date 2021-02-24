package com.weboot.springboot.practic;

public class PhoneDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.call("林");
        System.out.println("----------------");
        NewPhone newPhone = new NewPhone();
        newPhone.call("hello");
    }
}
