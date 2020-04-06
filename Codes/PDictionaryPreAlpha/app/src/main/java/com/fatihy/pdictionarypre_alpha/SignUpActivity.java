package com.fatihy.pdictionarypre_alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
EditText email, password, passwordRe;
Button confirmButton;
private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = findViewById(R.id.emailSignIn);
        password = findViewById(R.id.passwordSignIn);
        passwordRe = findViewById(R.id.passwordReSignIn);
        confirmButton = findViewById(R.id.confirmSign);
    mAuth = FirebaseAuth.getInstance();
    }


    public void signInConfirm(View view)
    {
         final String emailTemp = email.getText().toString();
         final String passwordTemp = password.getText().toString();
         final String passwordReTemp = passwordRe.getText().toString();

        if(!passwordTemp.equals(passwordReTemp))
        {
            Toast.makeText(getApplicationContext(), "Your passwords doesn't match!", Toast.LENGTH_SHORT).show();
        }
        if(emailTemp.equals("") || !emailTemp.matches(".*@.*"))
        {
            Toast.makeText(getApplicationContext(), "Email is wrong or empty!", Toast.LENGTH_SHORT).show();
        }
        else
        {
        mAuth.createUserWithEmailAndPassword(emailTemp,passwordTemp).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getApplicationContext(), "Your account has been created successfully. ", Toast.LENGTH_SHORT).show();

                mAuth.signInWithEmailAndPassword(emailTemp,passwordTemp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        }
    }
}
