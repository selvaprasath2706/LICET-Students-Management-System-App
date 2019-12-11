package com.example.licet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.EventListener;

public class Main10Activity extends AppCompatActivity {
    String reg,value,avg;
    int i=0;
    double avgval=0.0;
    TextView t1,t2;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        reg = getIntent().getStringExtra("reg");
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        databaseReference=FirebaseDatabase.getInstance().getReference("licet");
        findvalue();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayvalue();
            }
        }, 1000);
}

    public void displayvalue() {
    databaseReference.child("student").child(reg).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange( DataSnapshot dataSnapshot) {

            if (dataSnapshot.hasChild("attper")) {
                String avgexactvalue = dataSnapshot.child("attper").child("value").getValue().toString();
                t2.setText(avgexactvalue);
            } else {
                Toast.makeText(Main10Activity.this, "Sorry for the interruption please try again Thank you", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Main10Activity.this,Main4Activity.class);
                startActivity(i);
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

    public void findvalue() {
        databaseReference.child("student").child(reg).child("percentage").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Averagevalue a=dataSnapshot1.getValue(Averagevalue.class);
                    avgval+=a.getAvg();
                    i++;
                    double avg=avgval/i;
                    databaseReference.child("student").child(reg).child("attper").child("value").setValue(avg);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}