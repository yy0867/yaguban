package com.harry.yaguban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.harry.yaguban.dummy.FragmentList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Player> batterList = new ArrayList<>();

    Fragment batterListFragment;
    Fragment pitcherListFragment;
    Fragment managePlayerFragment;
    Fragment matchListFragment;
    BottomNavigationView navView;

    Bundle dataBundle;

    private void initFragment() {
        batterListFragment = new BatterListFragment();
        pitcherListFragment = new PitcherListFragment();
        managePlayerFragment = new ManagePlayerFragment();
        matchListFragment = new MatchListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));

        //Create Object
        initFragment();
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
                    changeFragment(FragmentList.BATTERLIST);
                    return true;
                case R.id.bottom_nav_pitcher:
                    changeFragment(FragmentList.PITCHERLIST);
                    return true;
                case R.id.bottom_nav_match_list:
                    changeFragment(FragmentList.MATCHLIST);
                    return true;
                case R.id.bottom_nav_manage_player:
                    changeFragment(FragmentList.MANAGEPLAYER);
                    return true;
            }

            return false;
        }
    };

    public void changeFragment(FragmentList list) {
        switch(list) {
            case BATTERLIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, batterListFragment).commit();
                break;
            case PITCHERLIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, pitcherListFragment).commit();
                break;
            case MATCHLIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, matchListFragment).commit();
                break;
            case MANAGEPLAYER:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, managePlayerFragment).commit();
                break;
        }
    }

    public void saveFragmentData(Bundle bundle) {
        this.dataBundle = bundle;
    }
}