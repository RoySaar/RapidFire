package com.saar.roy.millionaire;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog dialog;
    Dialog alertDialog;
    TextView alertTxt;
    Button alertBtn;
    Button start;
    Resources res;
    CountDownTimer timer;
    Question question;
    String[][] answers;
    int[] correctNums;
    int questionNum;
    int timePast;
    ImageView iv;
    TextView timerTxt;
    final int LAST = 3;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button[] buttons;
    Animation animSlideOut;
    Animation animSlideOut2;
    Animation animFadeOut;
    Animation animSlideIn;
    Bundle extras;
    int timeToSet;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        res = getResources();
        extras = getIntent().getExtras();
        timeToSet = extras.getInt("timePerQuestion");
        timer = new CountDownTimer(timeToSet * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTxt.setText(String.valueOf(millisUntilFinished / 1000));
                timePast++;
            }

            @Override
            public void onFinish() {
                final MediaPlayer wrong = MediaPlayer.create(LevelActivity.this, R.raw.wrong);
                wrong.start();
                gameReset(false);
            }
        };
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        buttons = new Button[]{button1, button2, button3, button4};
        for (Button btn : buttons) {
            btn.setOnClickListener(this);
        }
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setBackgroundResource(R.drawable.image1);
        timerTxt = (TextView) findViewById(R.id.timerTxt);
        animSlideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);
        animSlideOut2 = AnimationUtils.loadAnimation(this, R.anim.slide_out2);
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        animSlideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        for (Button btn : buttons) {
            btn.setBackgroundColor(Color.parseColor("#E9E9E9"));
        }
        questionNum = 0;
        answers = new String[][]{res.getStringArray(R.array.answers1), res.getStringArray(R.array.answers2), res.getStringArray(R.array.answers3)};
        correctNums = new int[]{res.getInteger(R.integer.num1), res.getInteger(R.integer.num2), res.getInteger(R.integer.num3)};
        question = new Question(answers[questionNum], correctNums[questionNum]);
        timePast = 0;
        createStartDialog();

    }

    public void createStartDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_start);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setTitle("Press Start To Start");
        start = (Button) dialog.findViewById(R.id.start);
        start.setOnClickListener(this);
        dialog.show();
    }

    public void gameReset(boolean won) {
        timer.cancel();
        if (won) {
            timer.cancel();
            Intent intent = new Intent(LevelActivity.this, MainActivity.class);
            int scoreBy = 100 / (extras.getInt("timePerQuestion") * LAST);
            String time = "SCORE:" + String.valueOf(1000 - (scoreBy * timePast * 10));
            intent.putExtra("time", time);
            intent.putExtra("won", won);
            intent.putExtra("left", true);
            startActivity(intent);
            //Toast.makeText(this,"YOU WON!",Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(LevelActivity.this, MainActivity.class);
            String time = "TRY AGAIN";
            intent.putExtra("time", time);
            intent.putExtra("left", true);
            startActivity(intent);
        }
        for (Button btn : buttons) {
            btn.startAnimation(animSlideOut2);
        }
        iv.startAnimation(animSlideOut2);
        for (Button btn : buttons) {
            btn.setBackgroundColor(Color.parseColor("#E9E9E9"));
        }
        questionNum = 0;
        iv.setBackgroundResource(R.drawable.image1);
        for (int i = 0; i < buttons.length; i++) {
            Button btn = buttons[i];
            btn.setText(answers[0][i]);
        }
        for (Button btn : buttons) {
            btn.startAnimation(animSlideIn);
        }
        iv.startAnimation(animSlideIn);
        //if (!won)
        //dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == alertBtn) {
            timer.cancel();
            Intent intent = new Intent(LevelActivity.this, MainActivity.class);
            intent.putExtra("left", false);
            startActivity(intent);
        }
        if (v == start) {
            dialog.dismiss();
            timer.start();
        }
        if (v == buttons[correctNums[questionNum]]) { //correct answer
            timer.cancel();
            final MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);
            correct.start();
            questionNum++;
            if (questionNum == LAST) { //win
                timer.cancel();
                gameReset(true);
                finish();
            }
            v.setBackgroundColor(Color.parseColor("#00FF00"));
            if (questionNum != 0 && questionNum != 3)
                Toast.makeText(LevelActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            for (Button btn : buttons) {
                btn.startAnimation(animSlideOut2);
            }
            iv.startAnimation(animSlideOut2);
            for (int i = 0; i < buttons.length; i++) {
                Button btn = buttons[i];
                String[] s1 = answers[questionNum];
                btn.setText(s1[i]);
            }
            for (Button btn : buttons) {
                btn.setBackgroundColor(Color.parseColor("#E9E9E9"));
            }
            if (questionNum == 1)
                iv.setBackgroundResource(R.drawable.image2);
            else if (questionNum == 2)
                iv.setBackgroundResource(R.drawable.image3);
            for (Button btn : buttons) {
                btn.startAnimation(animSlideIn);
            }
            iv.startAnimation(animSlideIn);
            if (questionNum != 0 && questionNum != 3)
                timer.start();
        } else if (v != start && v != alertBtn) { //wrong answer
            timer.cancel();
            Vibrator vibrator = (Vibrator) LevelActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(250);
            final MediaPlayer wrong = MediaPlayer.create(this, R.raw.wrong);
            wrong.start();
            v.setBackgroundColor(Color.parseColor("#881111"));
            Toast.makeText(this, "GAME OVER!", Toast.LENGTH_LONG).show();
            gameReset(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_level, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menuItem1) {
            createAlertDialog("", "Are You Sure?", "YES");
            alertDialog.show();
        }
        return true;
    }

    public void createAlertDialog(String msg, String title) {
        alertDialog = new Dialog(this);
        alertDialog.setContentView(R.layout.dialog_alert);
        alertDialog.setTitle(title);
        alertTxt = (TextView) alertDialog.findViewById(R.id.alertText);
        alertTxt.setText(msg);
        alertBtn = (Button) alertDialog.findViewById(R.id.alertBtn);
        alertBtn.setOnClickListener(this);
    }

    public void createAlertDialog(String msg, String title, String btnTxt) {
        alertDialog = new Dialog(this);
        alertDialog.setContentView(R.layout.dialog_alert);
        alertDialog.setTitle(title);
        alertTxt = (TextView) alertDialog.findViewById(R.id.alertText);
        alertTxt.setText(msg);
        alertBtn = (Button) alertDialog.findViewById(R.id.alertBtn);
        alertBtn.setText(btnTxt);
        alertBtn.setOnClickListener(this);
    }
}
