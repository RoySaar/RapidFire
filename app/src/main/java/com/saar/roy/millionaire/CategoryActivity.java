package com.saar.roy.millionaire;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlus;
    Button btnMinus;
    Button btnDivide;
    Button btnMultiply;
    Intent intent;
    Dialog alertDialog;
    TextView alertTxt;
    Button alertBtn;
    Menu menu;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);
        btnDivide = (Button)findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(this);
        btnMultiply = (Button)findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(this);
        time = getIntent().getIntExtra("timePerQuestion",5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlus:
                intent = new Intent(CategoryActivity.this,LevelActivity.class);
                intent.putExtra("category","plus");
                intent.putExtra("timePerQuestion", time);
                startActivity(intent);
                break;
            case R.id.btnMinus:
                intent = new Intent(CategoryActivity.this,LevelActivity.class);
                intent.putExtra("category","minus");
                intent.putExtra("timePerQuestion", time);
                startActivity(intent);
                break;
            case R.id.btnDivide:
                intent = new Intent(CategoryActivity.this,LevelActivity.class);
                intent.putExtra("category","divide");
                intent.putExtra("timePerQuestion", time);
                startActivity(intent);
                break;
            case R.id.btnMultiply:
                intent = new Intent(CategoryActivity.this,LevelActivity.class);
                intent.putExtra("category","multiply");
                intent.putExtra("timePerQuestion", time);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_level,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.menuItem1){
            createAlertDialog("","Are You Sure?","YES");
            alertDialog.show();
        }
        return true;
    }

    public void createAlertDialog(String msg, String title,String btnTxt) {
        alertDialog = new Dialog(this);
        alertDialog.setContentView(R.layout.dialog_alert);
        alertDialog.setTitle(title);
        alertTxt = (TextView)alertDialog.findViewById(R.id.alertText);
        alertTxt.setText(msg);
        alertBtn = (Button)alertDialog.findViewById(R.id.alertBtn);
        alertBtn.setText(btnTxt);
        alertBtn.setOnClickListener(this);
    }
}
