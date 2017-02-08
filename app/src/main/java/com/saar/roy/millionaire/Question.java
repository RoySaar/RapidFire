package com.saar.roy.millionaire;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Roy-PC on 1/27/2017.
 */
public class Question {
    String[] answers;
    private int num1;
    private int num2;
    private int correctNum;
    private int correctValue;
    final int NUM_OF_ANSWERS = 4;

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public Question(String[] answersArr,int correctPos) {
        answers = answersArr;
        correctNum = correctPos;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

}
