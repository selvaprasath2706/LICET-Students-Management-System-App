package com.example.licet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Main8Activity extends AppCompatActivity {
    Spinner s1,s2;
    String stname,streg,styear,stdept,stph,stadd,stfn,stmn,stfnum,stmnum,year,department;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        s1=findViewById(R.id.spinner);
        s2=findViewById(R.id.spinner2);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView4);
        t5=findViewById(R.id.textView5);
        t6=findViewById(R.id.textView6);
        t7=findViewById(R.id.textView7);
        t8=findViewById(R.id.textView8);
        t9=findViewById(R.id.textView9);
        t10=findViewById(R.id.textView10);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        e5=findViewById(R.id.editText5);
        e6=findViewById(R.id.editText6);
        e7=findViewById(R.id.editText7);
        e8=findViewById(R.id.editText8);
        databaseReference= FirebaseDatabase.getInstance().getReference("licet");
        databaseReference2= FirebaseDatabase.getInstance().getReference("licetyear");
        List<String> l1=new ArrayList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l1.add("4");
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,l1);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                styear=adapterView.getItemAtPosition(i).toString();
                year=styear;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> l2=new ArrayList<>();
        l2.add("IT");
        l2.add("MECH");
        l2.add("EEE");
        l2.add("ECE");
        l2.add("CSE");
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,l2);
        s2.setAdapter(adapter2);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stdept=adapterView.getItemAtPosition(i).toString();
                department=stdept;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void registerdata(View view) {
    stname=e1.getText().toString();
    streg=e2.getText().toString();
    stph=e3.getText().toString();
    stadd=e4.getText().toString();
    stfn=e5.getText().toString();
    stmn=e6.getText().toString();
    stfnum=e7.getText().toString();
    stmnum=e8.getText().toString();
    if(!streg.isEmpty())
    {
        if(!stname.isEmpty())
        {
            if(!stph.isEmpty())
            {
                if(!stfn.isEmpty())
                {
                    if(!stmn.isEmpty())
                    {
                        if(!stadd.isEmpty())
                        {
                            databaseReference.child("student").child(streg).child("name").setValue(stname);
                            databaseReference.child("student").child(streg).child("reg").setValue(streg);
                            databaseReference.child("student").child(streg).child("year").setValue(styear);
                            databaseReference.child("student").child(streg).child("dept").setValue(stdept);
                            databaseReference.child("student").child(streg).child("phone").setValue(stph);
                            databaseReference.child("student").child(streg).child("fathername").setValue(stfn);
                            databaseReference.child("student").child(streg).child("mothername").setValue(stmn);
                            databaseReference.child("student").child(streg).child("fathernum").setValue(stfnum);
                            databaseReference.child("student").child(streg).child("mothernum").setValue(stmnum);
                            databaseReference.child("student").child(streg).child("address").setValue(stadd);


                            databaseReference2.child("student").child(department).child(year).child(streg).child("name").setValue(stname);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("reg").setValue(streg);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("year").setValue(styear);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("dept").setValue(stdept);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("phone").setValue(stname);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("fathername").setValue(stfn);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("mothername").setValue(stmn);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("fathernum").setValue(stfnum);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("mothernum").setValue(stmnum);
                            databaseReference2.child("student").child(department).child(year).child(streg).child("address").setValue(stadd);





                            Toast.makeText(Main8Activity.this, "Succesfully Entered", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Main8Activity.this,Main2Activity.class);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(Main8Activity.this, "Please Enter the Address", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Main8Activity.this, "Please Enter the Mother name", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Main8Activity.this, "Please Enter the father name", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(Main8Activity.this, "Please Enter the phone number", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Main8Activity.this, "Please Enter the name", Toast.LENGTH_SHORT).show();
        }

    }

    else
    {
        Toast.makeText(Main8Activity.this, "Please enter the Register number", Toast.LENGTH_SHORT).show();
    }
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main7Activity.class);
        startActivity(intent1);
    }

}
