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

public class SignUp extends AppCompatActivity {

    private EditText etUsername, etPassword, etOrganization, etPhoneNumber;
    private Button signUp;
    private DatabaseReference reference;
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
        member = new Member();
        reference = FirebaseDatabase.getInstance().getReference().child("Members");
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
            member.setName(etUsername.getText().toString().trim());
            member.setPassword(etPassword.getText().toString().trim());
            member.setPhoneNumber(etPhoneNumber.getText().toString().trim());
            member.setHospitalOrClinic(etOrganization.getText().toString().trim());
            member.setBranch(spinner.getSelectedItem().toString().trim());
            reference.child(etUsername.getText().toString().trim()).setValue(member);
            Toast.makeText(SignUp.this, "Registered Successfully...", Toast.LENGTH_LONG).show();
        }
    }
}