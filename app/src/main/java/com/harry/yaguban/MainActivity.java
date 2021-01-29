package com.harry.yaguban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment batterListFragment;
    Fragment pitcherListFragment;
    Fragment addPlayerFragment;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));

        //Create Object
        batterListFragment = new BatterListFragment();
        pitcherListFragment = new PitcherListFragment();
        addPlayerFragment = new AddPlayerFragment();
        navView = findViewById(R.id.bottom_nav_view);

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        //Fragment Manager
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, batterListFragment).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.bottom_nav_batter:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, batterListFragment).commit();
                    return true;
                case R.id.bottom_nav_pitcher:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, pitcherListFragment).commit();
                    return true;
                case R.id.bottom_nav_add_player:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, addPlayerFragment).commit();
                    return true;
            }

            return false;
        }
    };
}