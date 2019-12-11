package com.example.licet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main14Activity extends AppCompatActivity {
    Spinner s1, s2, s3;
    Button b1,b2,b3,b4;
    FirebaseAuth mAuth;
    int spi1,spi2,spi3,spi4,spi5,spi6,spi7,spi8;
    String st1, st2, st3, date, styear, stdept, stperiod,stabsreg,reg;
    String sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference3;
    EditText e1,e2;
    int stnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        s1 = findViewById(R.id.spinner);
        s2 = findViewById(R.id.spinner2);
        s3 = findViewById(R.id.spinner3);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        mAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("licetyear");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("licetleave");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("licet");
        List<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l1.add("4");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, l1);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                st1 = adapterView.getItemAtPosition(i).toString();
                styear = st1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> l2 = new ArrayList<>();
        l2.add("IT");
        l2.add("MECH");
        l2.add("EEE");
        l2.add("ECE");
        l2.add("CSE");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, l2);
        s2.setAdapter(adapter2);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                st2 = adapterView.getItemAtPosition(i).toString();
                stdept = st2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> l3 = new ArrayList<>();
        l3.add("1");
        l3.add("2");
        l3.add("3");
        l3.add("4");
        l3.add("5");
        l3.add("6");
        l3.add("7");
        l3.add("8");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, l3);
        s3.setAdapter(adapter3);
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                st3 = adapterView.getItemAtPosition(i).toString();
                stperiod = st3;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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
        DatePickerDialog d = new DatePickerDialog(this, selva, i3, i2, i1);
        d.show();



    }


    public void absent(View view) {
        stabsreg=e2.getText().toString();
        String num=e1.getText().toString();
        if(date!=null)
        {
            if(!num.isEmpty())
            {
                stnumber=Integer.parseInt(String.valueOf(e1.getText()));
                if(!stabsreg.isEmpty())
                {
                    if(stnumber>1) {
                        int i;
                        String s11, s12, s13;
                        String abs[] = new String[stnumber];
                        String values[] = stabsreg.split(",");
                        abs[0] = values[0];
                        s11 = stabsreg.substring(0, 10);
                        for (i = 1; i < stnumber; i++) {
                            s12 = values[i];
                            s13 = s11 + s12;
                            abs[i] = s13;
                        }

                        for (i = 0; i < stnumber; i++) {
                            String tempreg = abs[i];
                            databaseReference2.child("student").child(tempreg).child("leave").child(date).child(stperiod).child("status").setValue("a");
                            databaseReference3.child("leave").child(date).child(tempreg).child("reg").setValue(tempreg);
                            databaseReference3.child("leave").child(date).child(tempreg).child("date").setValue(date);
                            databaseReference.child("student").child(stdept).child(styear).child(tempreg).child("leave").child(date).child(stperiod).child("status").setValue("a");
                        }
                    }
                    else if(stnumber==1)
                    {
                        databaseReference2.child("student").child(stabsreg).child("leave").child(date).child(stperiod).child("status").setValue("a");
                        databaseReference3.child("leave").child(date).child(stabsreg).child("reg").setValue(stabsreg);
                        databaseReference3.child("leave").child(date).child(stabsreg).child("date").setValue(date);
                        databaseReference.child("student").child(stdept).child(styear).child(stabsreg).child("leave").child(date).child(stperiod).child("status").setValue("a");


                    }

                }
                else
                {
                    Toast.makeText(Main14Activity.this, "Please Enter the absentee reg no's", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(Main14Activity.this, "Please enter the number of absentee", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Main14Activity.this, "Please select the date", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(Main14Activity.this, "Absent Marked", Toast.LENGTH_SHORT).show();
        Intent i2=new Intent(Main14Activity.this,Main2Activity.class);
        startActivity(i2);
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main2Activity.class);
        startActivity(intent1);
    }
}
