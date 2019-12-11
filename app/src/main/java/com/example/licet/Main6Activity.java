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

public class Main6Activity extends AppCompatActivity {
    String regno;
    RecyclerView r;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        regno=getIntent().getStringExtra("regno");
        r=findViewById(R.id.recycler);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            databaseReference= FirebaseDatabase.getInstance().getReference("licet").child("student").child(regno).child("absent").child("leave");


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Data,recycle> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Data, recycle>(
                Data.class,
                R.layout.data,
                recycle.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(recycle recycle, Data data, int i) {
                recycle.setDate(data.getDate());
                recycle.setPeriod(data.getPeriods());
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

        public void setPeriod(String period){
            TextView t2= mview.findViewById(R.id.textView2);
            t2.setText(period);
        }



    }

}
