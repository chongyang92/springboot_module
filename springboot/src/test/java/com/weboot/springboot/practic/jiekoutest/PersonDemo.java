package com.weboot.springboot.practic.jiekoutest;

public class PersonDemo {
    public static void main(String[] args) {
        PingpangPlayer pingpangPlayer = new PingpangPlayer("wang",32);
        System.out.println(pingpangPlayer);
        pingpangPlayer.eat();
        pingpangPlayer.study();
        pingpangPlayer.speakEnglish();

        BasketballPlayer basketballPlayer = new BasketballPlayer("yaoming",46);
        System.out.println(basketballPlayer);
        basketballPlayer.eat();
        basketballPlayer.study();
    }
}
