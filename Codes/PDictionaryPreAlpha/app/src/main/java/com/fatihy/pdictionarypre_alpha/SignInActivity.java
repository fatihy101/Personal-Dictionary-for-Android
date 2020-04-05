package com.fatihy.pdictionarypre_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
EditText email, password, passwordRe;
Button confirmButton; 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = findViewById(R.id.emailSignIn);
        password = findViewById(R.id.passwordSignIn);
        passwordRe = findViewById(R.id.passwordReSignIn);
        confirmButton = findViewById(R.id.confirmSign);
    }
    
    public void signInConfirm(View view)
    {
        if(!password.getText().toString().equals(passwordRe.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Your passwords doesn't match!", Toast.LENGTH_SHORT).show();
        }
        if(email.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Email section is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
