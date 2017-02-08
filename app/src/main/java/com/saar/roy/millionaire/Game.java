package com.saar.roy.millionaire;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Switch;

import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Roy-PC on 1/30/2017.
 */
public class Game {

    public Timer timer;
    private Question question;
    private int level;
    private int category;


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void genereateQuestion(){
        //question.setNum1(ThreadLocalRandom.current().nextInt(,));
        //question.setNum2(ThreadLocalRandom.current().nextInt(,));
    }
}
