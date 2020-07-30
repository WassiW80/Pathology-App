package com.example.pathology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponent();
    }

    private void initializeComponent(){
        username=findViewById(R.id.loginUsernameField);
        password=findViewById(R.id.loginPasswordField);
    }

    public void openLoginPage(View view){
        Intent intent=new Intent(Login.this,SignUp.class);
        startActivity(intent);
    }
}