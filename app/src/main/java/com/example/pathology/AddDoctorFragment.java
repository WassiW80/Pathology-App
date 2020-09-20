package com.example.pathology;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDoctorFragment extends Fragment {

    private EditText etUsername, etPassword, etOrganization, etPhoneNumber;
    private Button signUp;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private Member member;
    Spinner spinner;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_doctor, container, false);
        initializeComponent(view);
        addListenerOnSpinnerItemSelection(view);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateField()) {
                    String name = etUsername.getText().toString().trim();
                    String phoneNumber = etPhoneNumber.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    String organization = etOrganization.getText().toString().trim();
                    String branch = spinner.getSelectedItem().toString().trim();
                    member = new Member(name, password, organization, phoneNumber, branch);
                    reference.child(phoneNumber).setValue(member);
                    Toast.makeText(getActivity(), "Registered Successfully...", Toast.LENGTH_LONG).show();
                    clearField();
                }
            }
        });
        return view;
    }

    private void initializeComponent(View view) {
        etUsername = view.findViewById(R.id.signUpNameField);
        etPhoneNumber = view.findViewById(R.id.phoneNumberField);
        etPassword = view.findViewById(R.id.signUpPasswordField);
        etOrganization = view.findViewById(R.id.signUpOrganizationField);
        signUp = view.findViewById(R.id.signUpButton);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");
    }

    public void addListenerOnSpinnerItemSelection(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
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
            Toast.makeText(getActivity(), "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate mobile number
        if (mobile.length() != 10) {
            Toast.makeText(getActivity(), "Please enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate password
        if (password.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 5) {
            Toast.makeText(getActivity(), "Password length minimum 5", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (organization.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter organization", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (spinner.getSelectedItem().toString().trim().equals("Branch")) {
            Toast.makeText(getActivity(), "Select your branch", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
/*

    public void validation(View view) {
        if (validateField()) {
            String name = etUsername.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            String password="doctor123";
            etPassword.setText(password);
            String organization = etOrganization.getText().toString().trim();
            String branch = spinner.getSelectedItem().toString().trim();
            member = new Member(name, password, organization, phoneNumber, branch);
            reference.child(phoneNumber).setValue(member);
            Toast.makeText(getActivity(), "Registered Successfully...", Toast.LENGTH_LONG).show();
            clearField();
        }
    }
*/

    private void clearField() {
        etUsername.getText().clear();
        etPhoneNumber.getText().clear();
        etOrganization.getText().clear();
        etPassword.getText().clear();
        spinner.setSelection(0);
    }
}