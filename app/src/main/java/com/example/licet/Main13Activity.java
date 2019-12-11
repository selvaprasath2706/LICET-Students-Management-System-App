package com.example.licet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main13Activity extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    EditText e1,e2,e3,e4;
    String pass,email,newPwd,newpwd2;
FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView4);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

    }

    public void changepassword(View view) {
    email=e1.getText().toString();
    pass=e2.getText().toString();
      newPwd = e3.getText().toString();
      newpwd2=e4.getText().toString();
      if(newPwd.equals(newpwd2)) {
          if (!validateResetPassword(newPwd)) {
              Toast.makeText(Main13Activity.this, "Invalid password, please enter valid Strong  password", Toast.LENGTH_SHORT).show();
              return;
          }
          AuthCredential credential = EmailAuthProvider.getCredential(email, pass);
          user.reauthenticate(credential)
                  .addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if (task.isSuccessful()) {
                              user.updatePassword(newPwd)
                                      .addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {
                                              if (task.isSuccessful()) {
                                                  Toast.makeText(Main13Activity.this, "Password has been updated", Toast.LENGTH_SHORT).show();
                                                  mAuth.signOut();
                                                  Intent i=new Intent(Main13Activity.this,Main2Activity.class);
                                                  startActivity(i);
                                              } else {
                                                  Toast.makeText(Main13Activity.this, "Failed to update password.Please Enter the valid user name ans password", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      });
                          }
                      }
                  });
      }
      else
      {
          Toast.makeText(Main13Activity.this, "Please type the same password at both the textboxes", Toast.LENGTH_SHORT).show();
      }
    }
    private boolean validateResetPassword(String password) {
        boolean valid = true;
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            valid = false;
        }
        return valid;
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main2Activity.class);
        startActivity(intent1);
    }
}
