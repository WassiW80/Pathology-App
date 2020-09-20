package com.example.pathology;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPasswordFragment extends Fragment {

    private EditText etNewPassword, etConfirmPassword;
    private Button resetButton;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        initializeComponent(view);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateField())
                    if (isPasswordChanged()) {
                        Toast.makeText(getActivity(), "Password Changed Successfully...", Toast.LENGTH_LONG).show();
                        clearField();
                    }
            }
        });
        return view;

    }

    private void initializeComponent(View view) {
        etNewPassword = view.findViewById(R.id.newPassword);
        etConfirmPassword = view.findViewById(R.id.confirmPassword);
        resetButton = view.findViewById(R.id.reset);
        reference = FirebaseDatabase.getInstance().getReference("User");
    }

    private boolean validateField() {
        String newPass = etNewPassword.getText().toString();
        String confirmPass = etConfirmPassword.getText().toString();
        //validate password
        if (newPass.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPass.length() < 5) {
            Toast.makeText(getActivity(), "Password length minimum 5", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!confirmPass.equals(newPass)) {
            Toast.makeText(getActivity(), "Password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

  /*  public void updatePassword(View view) {
        if (validateField())
            if (isPasswordChanged()) {
                Toast.makeText(getActivity(), "Password Changed Successfully...", Toast.LENGTH_LONG).show();
                clearField();
            }
    }
*/
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