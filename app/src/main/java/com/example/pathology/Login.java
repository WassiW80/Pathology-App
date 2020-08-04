package com.example.pathology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponent();
    }

    private void initializeComponent() {
        username = findViewById(R.id.loginUsernameField);
        password = findViewById(R.id.loginPasswordField);
        login = findViewById(R.id.loginButton);
    }

    public void openLoginPage(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
    }
}