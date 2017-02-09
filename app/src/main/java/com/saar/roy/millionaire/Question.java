package com.saar.roy.millionaire;

/**
 * Created by Eidan on 2/9/2017.
 */
public class Question {
    public final String question;

    private String[] answers;

    public Question(String question, String[] answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public final int correctAnswer;


}
