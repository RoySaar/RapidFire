package com.saar.roy.millionaire;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItem;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.saar.roy.millionaire.CircularSeekBar;

/**
 * Created by Roy-PC on 1/28/2017.
 */
public class MainActivity  extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    Button homeBtn;
    //ImageButton floatButton;
    Dialog dialog;
    Dialog scoreAlert;
    Dialog timeAlert;
    Button alertBtn;
    Button setButton;
    Button scoreAlertBtn;
    SeekBar seekBar;
    TextView timeSetTxt;
    TextView score;
    TextView alertTxt;
    int timePerQuestion;
    int timeToSet;
    Animation animRotate;
    Animation animRotate2;
    boolean timeIsSet;
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animRotate2 = AnimationUtils.loadAnimation(this, R.anim.rotate2);
        homeBtn = (Button) findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);
        //floatButton = (ImageButton) findViewById(R.id.floatButton);
        //floatButton.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        timePerQuestion = 5;
        timeToSet = 5;
        createOptionsDialog();
        if (extras != null) {
            if (extras.getBoolean("left")) {
                if (extras.getBoolean("won")) {
                    Toast.makeText(this, "YOU WON!", Toast.LENGTH_LONG).show();
                    final MediaPlayer congratulations = MediaPlayer.create(this, R.raw.congratulations);
                    congratulations.start();
                }
                String time = extras.getString("time");
                TextView textView = (TextView) findViewById(R.id.wonIn);
                textView.setText(time);
            }
        }
        timeIsSet = false;
        //createScoreDialog(time);
    }

    @Override
    public void onClick(View v) {
        if (v == homeBtn) {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            intent.putExtra("timePerQuestion", timePerQuestion);
            startActivity(intent);
        }
  /*      if (v == floatButton) {
            floatButton.startAnimation(animRotate);
            dialog.show();
        } */
        if (v == setButton) {
            timePerQuestion = seekBar.getProgress() + 1;
            if (!timeIsSet){
                menu.getItem(0).setIcon(R.drawable.alarm_check);
                timeIsSet = true;
            }
            dialog.dismiss();
            //floatButton.startAnimation(animRotate2);
        }
        if (v == scoreAlertBtn) {
            scoreAlert.dismiss();
        }
        if(v == alertBtn){
            timeAlert.dismiss();
        }
    }

    public void createOptionsDialog() {
        dialog = new Dialog(this);
        dialog.setOnShowListener(this);
        dialog.setOnDismissListener(this);
        dialog.setContentView(R.layout.dialog_options);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle("Time Per Question:");
        timeSetTxt = (TextView) dialog.findViewById(R.id.timeSetTxt);
        timeSetTxt.setText(String.valueOf(timePerQuestion));
        seekBar = (SeekBar) dialog.findViewById(R.id.timeSetBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(timePerQuestion - 1);
        setButton = (Button) dialog.findViewById(R.id.timeSetBtn);
        setButton.setOnClickListener(this);
        //dialog.show();
    }

    public void createScoreDialog(String time) {
        scoreAlert = new Dialog(this);
        scoreAlert.setContentView(R.layout.dialog_score);
        //scoreAlert.setTitle("SCORE:");
        score = (TextView) scoreAlert.findViewById(R.id.scoreAlertValue);
        score.setText(time);
        scoreAlertBtn = (Button) scoreAlert.findViewById(R.id.scoreAlertBtn);
        scoreAlertBtn.setOnClickListener(this);
        scoreAlert.show();
    }

    public void createAlertDialog(String msg, String title) {
        timeAlert = new Dialog(this);
        timeAlert.setContentView(R.layout.dialog_alert);
        timeAlert.setTitle(title);
        alertTxt = (TextView)timeAlert.findViewById(R.id.alertText);
        alertTxt.setText(msg);
        alertBtn = (Button)timeAlert.findViewById(R.id.alertBtn);
        alertBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.about){
            Toast.makeText(this,"Si",Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId() == R.id.menuItem1 && !timeIsSet){
            dialog.show();
        }
        else if (timeIsSet){
            createAlertDialog("TIME IS ALREADY SET TO "+timePerQuestion+" SECONDS","ATTENTION:");
            timeAlert.show();
        }
        return true;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        timeSetTxt.setText(String.valueOf(progress+1));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onShow(DialogInterface dialog) {
        timeSetTxt.setText(String.valueOf(timePerQuestion));
        seekBar.setProgress(timePerQuestion-1);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }
}
