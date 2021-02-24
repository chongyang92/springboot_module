package com.weboot.springboot.practic.exception;

import java.util.Scanner;

public class TeacherTest {
    public static void main(String[] args) throws ScoreException {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        Teacher teacher = new Teacher();
        teacher.checkScore(score);
        System.out.println("结束");
    }
}
