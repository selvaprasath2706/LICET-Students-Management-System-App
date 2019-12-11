package com.example.licet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    //    android:background="#FF7E00"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setupBottomNavigationView();
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.findabsentees:
                Intent i2 = new Intent(Main2Activity.this, Main12Activity.class);
                startActivity(i2);
                break;
            case R.id.myaccount:
                Intent i1 = new Intent(Main2Activity.this, Main13Activity.class);
                startActivity(i1);
                break;
            case R.id.register:
                Intent i8=new Intent(Main2Activity.this,Main7Activity.class);
                startActivity(i8);
                break;

            case R.id.home:
                Intent i3 = new Intent(Main2Activity.this, Main2Activity.class);
                startActivity(i3);
                break;
            case R.id.logout:
                mAuth.signOut();
                Intent i4 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.entry:
                        Intent i5 = new Intent(Main2Activity.this, Main3Activity.class);
                        startActivity(i5);
                        break;
                    case R.id.search:
                        Intent i6=new Intent(Main2Activity.this,Main4Activity.class);
                        startActivity(i6);
                        break;
                    case R.id.markabs:
                        Intent i7=new Intent(Main2Activity.this,Main14Activity.class);
                        startActivity(i7);
                        break;

                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Intent i = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
