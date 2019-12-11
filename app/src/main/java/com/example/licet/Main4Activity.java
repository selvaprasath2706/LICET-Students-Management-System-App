package com.example.licet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    EditText e1;
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        e1 = findViewById(R.id.editText);
    }

    public void search(View view) {
        st = e1.getText().toString();
        if (st.length() == 0) {
            Toast.makeText(Main4Activity.this, "Please enter the Register number", Toast.LENGTH_SHORT).show();
        } else if (st.length() != 12) {
            Toast.makeText(Main4Activity.this, "Please Enter a valid Register number", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(Main4Activity.this, Main5Activity.class);
            i.putExtra("reg", st);
            startActivity(i);
        }

    }
    @Override
    public void onBackPressed() {
     Intent intent1=new Intent(this,Main2Activity.class);
     startActivity(intent1);
    }
}
