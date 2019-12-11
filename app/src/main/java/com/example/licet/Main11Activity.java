package com.example.licet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main11Activity extends AppCompatActivity {
    TextView t;
    RecyclerView r;
    String date;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date=getIntent().getStringExtra("date");
        Toast.makeText(Main11Activity.this, "date"+date, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main11);
        databaseReference= FirebaseDatabase.getInstance().getReference("licetleave").child("leave").child(date);
        r=findViewById(R.id.recycler);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Absentstu,recycle> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Absentstu, recycle>(
                Absentstu.class,
                R.layout.data,
                recycle.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(recycle recycle, Absentstu absentstu, int i) {
                recycle.setDate(absentstu.getDate());
                recycle.setReg(absentstu.getReg());
            }

        };
        r.setAdapter(firebaseRecyclerAdapter);

    }
    public static class recycle extends RecyclerView.ViewHolder{
        View mview;
        public recycle(View itemView) {
            super(itemView);
            mview=itemView;
        }

        public void setDate(String date){
            TextView t1= mview.findViewById(R.id.textView1);
            t1.setText(date);
        }

        public void setReg(String period){
            TextView t2= mview.findViewById(R.id.textView2);
            t2.setText(period);
        }



    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main2Activity.class);
        startActivity(intent1);
    }
}
