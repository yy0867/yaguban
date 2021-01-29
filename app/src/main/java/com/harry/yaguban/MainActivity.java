package com.harry.yaguban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Player> batterList = new ArrayList<>();

    Fragment batterListFragment;
    Fragment pitcherListFragment;
    Fragment managePlayerFragment;
    Fragment matchListFragment;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));

        //Create Object
        batterListFragment = new BatterListFragment();
        pitcherListFragment = new PitcherListFragment();
        managePlayerFragment = new ManagePlayerFragment();
        matchListFragment = new MatchListFragment();
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
                case R.id.bottom_nav_match_list:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, matchListFragment).commit();
                    return true;
                case R.id.bottom_nav_manage_player:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, managePlayerFragment).commit();
                    return true;
            }

            return false;
        }
    };
}