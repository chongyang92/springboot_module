package com.weboot.springboot.practic.jiekoumingzuowei;

public class Jumppingoperator {
    public void useJumpping(Jumpping jumpping){
        jumpping.jump();
    }

    public Jumpping getJumpping(){
        Jumpping jumpping = new Cat();
        return jumpping;
    }
}
