package com.example.licet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main12Activity extends AppCompatActivity {
    public String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
    }
    public void dateselector(View view) {
        String d1, d2, d3;
        DatePickerDialog.OnDateSetListener selva = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int i0=Integer.parseInt(String.valueOf(i1));
                i0=i0+1;
                date = i2 + ":" + i0 + ":" + i;

            }
        };
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd:MM:yyyy");
        String date = s.format(c.getTime());
        String values[] = date.split(":");
        d1 = values[0];
        d2 = values[1];
        d3 = values[2];

        int i1, i2, i3;
        i1 = Integer.parseInt(d1);
        i2 = Integer.parseInt(d2);
        i3 = Integer.parseInt(d3);
        i2 = i2 - 1;
        DatePickerDialog d = new DatePickerDialog(Main12Activity.this, selva, i3, i2, i1);
        d.show();
    }

    public void movetonextpage(View view) {
        Intent intent=new Intent(Main12Activity.this,Main11Activity.class);
        intent.putExtra("date",date);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main2Activity.class);
        startActivity(intent1);
    }
}
