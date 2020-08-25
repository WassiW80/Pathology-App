package com.example.pathology;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    private EditText etNewPassword, etConfirmPassword;
    private Button resetButton;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initializeComponent();
    }

    private void initializeComponent() {
        etNewPassword = findViewById(R.id.newPassword);
        etConfirmPassword = findViewById(R.id.confirmPassword);
        resetButton = findViewById(R.id.reset);
        reference = FirebaseDatabase.getInstance().getReference("User");
    }

    private boolean validateField() {
        String newPass = etNewPassword.getText().toString();
        String confirmPass = etConfirmPassword.getText().toString();
        //validate password
        if (newPass.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPass.length() < 5) {
            Toast.makeText(this, "Password length minimum 5", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!confirmPass.equals(newPass)) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void updatePassword(View view) {
        if (validateField())
            if (isPasswordChanged()) {
                Toast.makeText(UserProfile.this, "Password Changed Successfully...", Toast.LENGTH_LONG).show();
                clearField();
            }
    }

    private boolean isPasswordChanged() {
        String newPassword = etNewPassword.getText().toString();
        if (!UserData.password.equals(newPassword)) {
            reference.child(UserData.phoneNumber).child("password").setValue(newPassword);
            UserData.password = newPassword;
            return true;
        }
        return false;
    }

    private void clearField() {
        etNewPassword.getText().clear();
        etConfirmPassword.getText().clear();
    }
}