package com.example.pathology;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DoctorMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        Toolbar toolbar = findViewById(R.id.doctor_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.doctor_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(DoctorMainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.doctor_navigation_view);
        navigationView.inflateHeaderView(R.layout.navigation_header);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelected(menuItem);
                return false;
            }
        });
    }

    private void UserMenuSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.doctor_nav_reports:
                break;

            case R.id.doctor_nav_settings:
                startActivity(new Intent(DoctorMainActivity.this, UserProfile.class));
                break;

            case R.id.doctor_nav_contact:
                startActivity(new Intent(DoctorMainActivity.this, AboutUs.class));
                break;

            case R.id.doctor_nav_logout:
                startActivity(new Intent(DoctorMainActivity.this, Login.class));
                finish();
                break;
        }
    }
}