package com.example.pathology.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pathology.Login;
import com.example.pathology.R;

public class LogoutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        startActivity(new Intent(getActivity(), Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        return view;
    }

}