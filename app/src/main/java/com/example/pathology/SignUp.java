package com.example.pathology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    private EditText username,password,confirmPassword,phoneNumber;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeComponent();
    }

    private void initializeComponent(){
        username=findViewById(R.id.signUpNameField);
        phoneNumber=findViewById(R.id.phoneNumberField);
        password=findViewById(R.id.signUpPasswordField);
        confirmPassword=findViewById(R.id.signUpConfirmPasswordField);
    }
}