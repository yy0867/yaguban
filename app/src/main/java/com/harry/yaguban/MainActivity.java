package com.harry.yaguban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));

        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.bottom_nav_batter:
                Toast.makeText(this, "batter!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bottom_nav_pitcher:
                Toast.makeText(this, "pitcher!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bottom_nav_add_player:
                Toast.makeText(this, "add player!", Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }
}