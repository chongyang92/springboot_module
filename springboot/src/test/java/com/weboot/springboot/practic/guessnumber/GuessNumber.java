package com.weboot.springboot.practic.guessnumber;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
   /* public static void main(String[] args) {
        start();
    }*/
    public GuessNumber() {
    }

    public static void start(){
        Random random = new Random();
        //int number = random.nextInt(100) + 1;
        int number = 93;
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入一个数字：");
            int guessNumber = scanner.nextInt();

            if(guessNumber > number){
                System.out.println("猜大了");
            }else if(guessNumber < number){
                System.out.println("猜小了");
            }else {
                System.out.println("恭喜，猜中了");
                break;
            }
        }
    }
}
