package com.saar.roy.millionaire;

import java.util.Random;

/**
 * Created by Eidan on 2/9/2017.
 */
public class QuestionGenerator {
    public static final int MAX_PARAMETER = 50;
    public static final int ANSWER_COUNT = 4;
    public static final int MIN_PARAMETER = 1;
    Random random;

    public QuestionGenerator() {
        this.random = new Random();
    }


    public Question generateQuestion(){
        int num1 = random.nextInt(MAX_PARAMETER) + MIN_PARAMETER;
        int num2 = random.nextInt(MAX_PARAMETER) + MIN_PARAMETER;
        String questionText = String.format("%d + %d", num1, num2);
        String[] answers = new String[ANSWER_COUNT];
        int correctPos = random.nextInt(ANSWER_COUNT);
        for (int i = 0; i < ANSWER_COUNT; i++) {
            if (i == correctPos)
                answers[i] = String.valueOf(num1+num2);
            else {
                answers[i] = String.valueOf(num1 + num2 + generateDevation());
            }
        }
        return new Question(questionText,answers,correctPos);
    }

    private int generateDevation() {
        int $ = random.nextInt(10) -5;
        if ($ == 0)
            $ = 5;
        return  $;
    }
}
