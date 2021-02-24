package com.weboot.springboot.practic.paixujihedaowenjian;

public class Student {
    private String name;
    private int chinese;
    private int english;
    private int math;

    public Student() {
    }

    public Student(String name, int chinese, int english, int math) {
        this.name = name;
        this.chinese = chinese;
        this.english = english;
        this.math = math;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", chinese=" + chinese +
                ", english=" + english +
                ", math=" + math +
                '}';
    }

    public int getSum(){
        return this.chinese + this.english +this.math;
    }
}
