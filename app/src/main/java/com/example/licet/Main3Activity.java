package com.example.licet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

public class Main3Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main3);
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
        DatePickerDialog d = new DatePickerDialog(Main3Activity.this, selva, i3, i2, i1);
        d.show();
    }






    public void calcaverage(final View view) {
        if(date==null)
        {
            Toast.makeText(Main3Activity.this, "Please select the date", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.child("student").child(stdept).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot contactSnapshot = dataSnapshot.child(styear);
                    Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                    for (DataSnapshot contact : contactChildren) {
                        registerno c = contact.getValue(registerno.class);
                        reg = c.getReg();
                        if(contact.child("leave").child(date).hasChild("8")) {
                            findavg(reg);
                        }
                        else
                        {
                            Toast.makeText(Main3Activity.this, "All 8 periods of data are not entered", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        }

    private void findavg(final String reg1) {

        databaseReference2.child("student").child(reg1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String absentperiods="";
                sp1=dataSnapshot.child("leave").child(date).child("1").child("status").getValue().toString();
                sp2=dataSnapshot.child("leave").child(date).child("2").child("status").getValue().toString();
                sp3=dataSnapshot.child("leave").child(date).child("3").child("status").getValue().toString();
                sp4=dataSnapshot.child("leave").child(date).child("4").child("status").getValue().toString();
                sp5=dataSnapshot.child("leave").child(date).child("5").child("status").getValue().toString();
                sp6=dataSnapshot.child("leave").child(date).child("6").child("status").getValue().toString();
                sp7=dataSnapshot.child("leave").child(date).child("7").child("status").getValue().toString();
                sp8=dataSnapshot.child("leave").child(date).child("8").child("status").getValue().toString();
                              if(sp1.equals("p"))
                                {
                                spi1=1;
                                 }
                               else if(sp1.equals("a"))
                               {
                                   spi1=0;
                                    absentperiods=absentperiods+"1";
                                }
                               if(sp2.equals("p"))
                                {
                                   spi2=1;
                                }
                                else if(sp2.equals("a"))
                                {
                                    spi2=0;
                                    absentperiods=absentperiods+"2";
                                }
                                if(sp3.equals("p"))
                                {
                                    spi3=1;
                                }
                               else  if(sp3.equals("a"))
                                {
                                    spi3=0;
                                   absentperiods=absentperiods+"3";
                                }
                                if(sp4.equals("p"))
                                {
                                    spi4=1;
                                }
                                else if(sp4.equals("a"))
                                {
                                    spi4=0;
                                    absentperiods=absentperiods+"4";
                                }
                                if(sp5.equals("p"))
                                {
                                    spi5=1;
                                }
                                else if(sp5.equals("a"))
                                {
                                    spi5=0;
                                    absentperiods=absentperiods+"5";
                               }
                                if(sp6.equals("p"))
                                {
                                    spi6=1;
                                }
                               else if(sp7.equals("a"))
                                {
                                    spi6=0;
                                    absentperiods=absentperiods+"6";
                                }
                                if(sp7.equals("p"))
                                {
                                    spi7=1;
                                }
                                else  if(sp7.equals("a"))
                                {
                                    spi7=0;
                                    absentperiods=absentperiods+"7";
                                }
                                if(sp8.equals("p"))
                                {
                                    spi8=1;
                                }
                               else if(sp8.equals("a"))
                                {
                                    spi8=0;
                                    absentperiods=absentperiods+"8";
                                }
                                double sum=spi1+spi2+spi3+spi4+spi5+spi6+spi7+spi8;
                               double avg=sum/8;
                               avg=avg*100;


                               databaseReference2.child("student").child(reg1).child("percentage").child(date).child("avg").setValue(avg);
                               databaseReference2.child("student").child(reg1).child("percentage").child(date).child("date").setValue(date);
                                if(absentperiods!="")
                                {
                                    databaseReference2.child("student").child(reg1).child("leave").child(date).child("absent periods").child("periods").setValue(absentperiods);
                                    databaseReference2.child("student").child(reg1).child("leave").child(date).child("absent periods").child("date").setValue(date);
                                    databaseReference2.child("student").child(reg1).child("absent").child("leave").child(date).child("periods").setValue(absentperiods);
                                    databaseReference2.child("student").child(reg1).child("absent").child("leave").child(date).child("date").setValue(date);

                                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Toast.makeText(Main3Activity.this,"Average calculated Successfully", Toast.LENGTH_SHORT).show();
        Intent i3=new Intent(Main3Activity.this,Main2Activity.class);
        startActivity(i3);
    }



    public void everyonepresent(View view) {
        if(date==null)
        {
            Toast.makeText(Main3Activity.this, "Please select the dates", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.child("student").child(stdept).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot contactSnapshot = dataSnapshot.child(styear);
                    Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                    for (DataSnapshot contact : contactChildren) {
                        registerno c = contact.getValue(registerno.class);
                        reg = c.getReg();
                        databaseReference2.child("student").child(reg).child("leave").child(date).child(stperiod).child("status").setValue("p");
                        databaseReference.child("student").child(stdept).child(styear).child(reg).child("leave").child(date).child(stperiod).child("status").setValue("p");
                    }

                }


                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

        }

        Toast.makeText(Main3Activity.this, "Marked present", Toast.LENGTH_SHORT).show();
       }
    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        Intent intent1=new Intent(this,Main2Activity.class);
        startActivity(intent1);
    }

}