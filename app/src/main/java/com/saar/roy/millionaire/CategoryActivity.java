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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlus:
                StartLevelActivity("plus");
                break;
            case R.id.btnMinus:
                StartLevelActivity("minus");
                break;
            case R.id.btnDivide:
                StartLevelActivity("divide");
                break;
            case R.id.btnMultiply:
                StartLevelActivity("multiply");
                break;
        }
    }

    private void StartLevelActivity(String plus) {
        Intent intent = new Intent(CategoryActivity.this, LevelActivity.class);
        intent.putExtra("category", plus);
        intent.putExtra("timePerQuestion", getIntent().getIntExtra("timePerQuestion",5));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_level,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.menuItem1){
            createAlertDialog("", "Are You Sure?", "YES").show();
        }
        return true;
    }

    public Dialog createAlertDialog(String msg, String title,String btnTxt) {
        Dialog alertDialog = new Dialog(this);
        alertDialog.setContentView(R.layout.dialog_alert);
        alertDialog.setTitle(title);
        TextView alertTxt = (TextView)alertDialog.findViewById(R.id.alertText);
        alertTxt.setText(msg);
        Button alertBtn = (Button)alertDialog.findViewById(R.id.alertBtn);
        alertBtn.setText(btnTxt);
        alertBtn.setOnClickListener(this);
        return alertDialog;
    }
}
