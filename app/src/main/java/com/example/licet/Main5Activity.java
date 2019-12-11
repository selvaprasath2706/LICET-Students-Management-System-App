package com.example.licet;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;

public class Main5Activity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19;
    String streg,stname,stphno,stfname,stmname,stfnum,stmnum,stadd,stdept,styear;
    Double avgval=0.0,totavg;
    int i=0;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        databaseReference= FirebaseDatabase.getInstance().getReference("licet");
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);
        t5 = findViewById(R.id.textView5);
        t6 = findViewById(R.id.textView6);
        t7 = findViewById(R.id.textView7);
        t8 = findViewById(R.id.textView8);
        t9 = findViewById(R.id.textView9);
        t10 = findViewById(R.id.textView10);
        t11 = findViewById(R.id.textView11);
        t12 = findViewById(R.id.textView12);
        t13 = findViewById(R.id.textView13);
        t14 = findViewById(R.id.textView14);
        t15 = findViewById(R.id.textView15);
        t16 = findViewById(R.id.textView16);
        t17 = findViewById(R.id.textView17);
        t18 = findViewById(R.id.textView18);
        t19 = findViewById(R.id.textView19);

        streg= getIntent().getStringExtra("reg");

    }

    @Override
    protected void onStart() {
        super.onStart();
    databaseReference.child("student").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.hasChild(streg)) {
                stname = dataSnapshot.child(streg).child("name").getValue().toString();
                stphno = dataSnapshot.child(streg).child("phone").getValue().toString();
                stfname = dataSnapshot.child(streg).child("fathername").getValue().toString();
                stmname = dataSnapshot.child(streg).child("mothername").getValue().toString();
                stfnum = dataSnapshot.child(streg).child("fathernum").getValue().toString();
                stmnum = dataSnapshot.child(streg).child("mothernum").getValue().toString();
                stadd = dataSnapshot.child(streg).child("address").getValue().toString();
                styear = dataSnapshot.child(streg).child("year").getValue().toString();
                stdept = dataSnapshot.child(streg).child("dept").getValue().toString();
                t2.setText(stname);
                t4.setText(styear);
                t6.setText(stdept);
                t8.setText(stphno);
                t10.setText(stfname);
                t12.setText(stmname);
                t14.setText(stfnum);
                t16.setText(stmnum);
                t18.setText(stadd);
            }
            else
            {
                Toast.makeText(Main5Activity.this, "The Register number you have entered is not available ", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(Main5Activity.this,Main4Activity.class);
                startActivity(intent1);
            }
            }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(Main5Activity.this, "Sorry the date you have entered seemed to be unavailable", Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(Main5Activity.this,Main4Activity.class);
            startActivity(intent1);
        }
    });
    }

    public void genreport(View view) {
        Intent i=new Intent(Main5Activity.this,Main6Activity.class);
        i.putExtra("regno",streg);
        startActivity(i);
    }

    public void calcpercentage(View view) {
       Intent i1=new Intent(Main5Activity.this,Main10Activity.class);
      i1.putExtra("reg",streg);
      startActivity(i1);
    }

    public void fathermobile(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + stfnum));
        startActivity(intent);
    }

    public void mothermobile(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + stmnum));
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main4Activity.class);
        startActivity(intent1);
    }

    public void callstudent(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + stphno));
        startActivity(intent);
    }
}
