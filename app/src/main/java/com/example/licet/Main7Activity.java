package com.example.licet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
    }

    public void switchmanual(View view) {
        Intent i1=new Intent(Main7Activity.this,Main8Activity.class);
        startActivity(i1);
    }

    public void switchspreadsheet(View view) {
        Intent i2=new Intent(Main7Activity.this,Main9Activity.class);
        startActivity(i2);
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main2Activity.class);
        startActivity(intent1);
    }
}
