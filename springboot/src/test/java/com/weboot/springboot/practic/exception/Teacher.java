package com.weboot.springboot.practic.exception;

public class Teacher {
    public void checkScore(int score) throws ScoreException{
        if(score < 0 || score > 100){
            throw new ScoreException("分数不在0到100之间");
        }else {
            System.out.println("分数正常");
        }
    }
}
