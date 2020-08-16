package com.example.pathology;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    private EditText etUsername, etPassword, etOrganization, etPhoneNumber;
    private Button signUp;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private Member member;
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

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("User");
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
    }

    private boolean validateField() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String organization = etOrganization.getText().toString();
        String mobile = etPhoneNumber.getText().toString();

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate mobile number
        if (mobile.length() != 10) {
            Toast.makeText(this, "Please enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
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
        if (organization.isEmpty()) {
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
            String name = etUsername.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String organization = etOrganization.getText().toString().trim();
            String branch = spinner.getSelectedItem().toString().trim();
            member = new Member(name,password,organization,phoneNumber,branch);
            reference.child(phoneNumber).setValue(member);
            Toast.makeText(SignUp.this, "Registered Successfully...", Toast.LENGTH_LONG).show();
            clearField();
        }
    }

    private void clearField() {
        etUsername.getText().clear();
        etPhoneNumber.getText().clear();
        etOrganization.getText().clear();
        etPassword.getText().clear();
        spinner.setSelection(0);
    }
}