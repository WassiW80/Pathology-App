package com.example.pathology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText etPhoneNumber, etPassword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponent();
    }

    private void initializeComponent() {
        etPhoneNumber = findViewById(R.id.loginUsernameField);
        etPassword = findViewById(R.id.loginPasswordField);
        login = findViewById(R.id.loginButton);
    }

    private boolean validateField() {
        String phoneNumber = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate password
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 5) {
            Toast.makeText(this, "Password length minimum 5", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void loginUser(View view) {
        if (validateField()) {
            isUser();
        }
    }

    private void isUser() {
        final String phoneNumber = etPhoneNumber.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (phoneNumber.equals("1234") && password.equals("admin")) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
            Query checkUser = reference.orderByChild("phoneNumber").equalTo(phoneNumber);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String passwordFromDB = snapshot.child(phoneNumber).child("password").getValue(String.class);
                        if (passwordFromDB.equals(password)) {
                            UserData.phoneNumber = snapshot.child(phoneNumber).child("phoneNumber").getValue(String.class);
                            UserData.password = snapshot.child(phoneNumber).child("password").getValue(String.class);
                            startActivity(new Intent(Login.this, DoctorMainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Wrong Password...", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "User Not Found...", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}