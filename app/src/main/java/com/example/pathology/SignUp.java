package com.example.pathology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirmPassword, etPhoneNumber;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeComponent();
    }

    private void initializeComponent() {
        etUsername = findViewById(R.id.signUpNameField);
        etPhoneNumber = findViewById(R.id.phoneNumberField);
        etPassword = findViewById(R.id.signUpPasswordField);
        etConfirmPassword = findViewById(R.id.signUpConfirmPasswordField);
    }

    public void openSignUpPage(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }

    private boolean validateField() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String mobile = etPhoneNumber.getText().toString();

        if (username.equals("")) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate mobile number
        if (mobile.length() != 10) {
            Toast.makeText(this, "Please enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate password
        if (password.equals("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 5) {
            Toast.makeText(this, "Password length minimum 5", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password does not matched", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void validation(View view) {
       if (validateField()){
            Toast.makeText(SignUp.this,"Registered Successfully...",Toast.LENGTH_LONG).show();
       }
    }
}