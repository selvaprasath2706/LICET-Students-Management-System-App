package com.example.licet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    EditText e1,e2;
    String s1,s2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        mAuth=FirebaseAuth.getInstance();
    }

    public void startsignin(View view) {
    s1=e1.getText().toString();
    s2=e2.getText().toString();
        mAuth.signInWithEmailAndPassword(s1, s2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Please enter a valid user name and password", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}
