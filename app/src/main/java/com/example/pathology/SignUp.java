package com.example.pathology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private EditText etUsername, etPassword, etOrganization, etPhoneNumber;
    private Button signUp;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeComponent();
        addListenerOnSpinnerItemSelection();
    }

    private void initializeComponent() {
        etUsername = findViewById(R.id.signUpNameField);
        etPhoneNumber = findViewById(R.id.phoneNumberField);
        etPassword = findViewById(R.id.signUpPasswordField);
        etOrganization = findViewById(R.id.signUpOrganizationField);
        signUp = findViewById(R.id.signUpButton);

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
    }

    public void openSignUpPage(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }

    private boolean validateField() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String organization = etOrganization.getText().toString();
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
        if (organization.equals("")) {
            Toast.makeText(this, "Please enter organization", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (spinner.getSelectedItem().toString().trim().equals("Branch")) {
            Toast.makeText(this, "Select your branch", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void validation(View view) {
        if (validateField()) {
            Toast.makeText(SignUp.this, "Registered Successfully...", Toast.LENGTH_LONG).show();
        }
    }
}