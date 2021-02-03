package com.harry.yaguban;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.harry.yaguban.dummy.FragmentList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Fragment currentMatchFragment;
    Fragment batterListFragment;
    Fragment pitcherListFragment;
    Fragment managePlayerFragment;
    Fragment matchListFragment;
    BottomNavigationView navView;
    Team ourTeam = new Team();
    Match curMatch = new Match(this);

    private void initFragment() {
        currentMatchFragment = new CurrentMatchFragment();
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
        loadTeam();

        navView = findViewById(R.id.bottom_nav_view);

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        //Fragment Manager
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, currentMatchFragment).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.bottom_nav_current_match:
                    changeFragment(FragmentList.CURRENTMATCH);
                    return true;
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
            case CURRENTMATCH:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, currentMatchFragment).commit();
                break;
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

    public void refreshFragment(FragmentList list) {
        switch(list) {
            case CURRENTMATCH:
                getSupportFragmentManager().beginTransaction()
                        .detach(currentMatchFragment)
                        .attach(currentMatchFragment)
                        .commit();
                break;
            case BATTERLIST:
                getSupportFragmentManager().beginTransaction()
                    .detach(batterListFragment)
                    .attach(batterListFragment)
                    .commit();
                break;
            case PITCHERLIST:
                getSupportFragmentManager().beginTransaction()
                        .detach(pitcherListFragment)
                        .attach(pitcherListFragment)
                        .commit();
                break;
            case MATCHLIST:
                getSupportFragmentManager().beginTransaction()
                        .detach(matchListFragment)
                        .attach(matchListFragment)
                        .commit();
                break;
            case MANAGEPLAYER:
                getSupportFragmentManager().beginTransaction()
                        .detach(managePlayerFragment)
                        .attach(managePlayerFragment)
                        .commit();
                break;
        }
    }

    public void launchAddPlayerPopup() {
        Intent intent = new Intent(this, AddPlayerPopupActivity.class);
        startActivityForResult(intent, 1);
    }

    public void launchAddMatchPopup() {
        Intent intent = new Intent(this, AddMatchPopupActivity.class);
        startActivityForResult(intent, 2);
    }

    public void launchEnterPlayerPopup(boolean position) {
        Intent intent = new Intent(this, EnterPlayerPopup.class);
        intent.putExtra("isBatter", position);
        startActivityForResult(intent, 3);
    }

    public void launchSetBatterPopup() {

    }

    private void loadTeam() {
        ourTeam = TeamFileManager.loadTeam(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            //Add Player
            if (resultCode == RESULT_OK && data != null) {
                String playerName = data.getStringExtra("playerName");
                Position playerPosition = (Position) data.getSerializableExtra("playerPosition");
                int playerBackNumber = data.getIntExtra("playerBackNumber", 1);

                loadTeam();
                ourTeam.addPlayer(new Player(playerName, playerBackNumber, playerPosition));
                TeamFileManager.saveTeam(this, ourTeam);

                refreshFragment(FragmentList.MANAGEPLAYER);
            }
        }
        else if (requestCode == 2) {
            //Add Match
            if (resultCode == RESULT_OK && data != null) {
                String opName = data.getStringExtra("team");
                String location = data.getStringExtra("location");
                String date = data.getStringExtra("date");

                curMatch = new Match(this);
                curMatch.startMatch();
                curMatch.setLocation(location);
                curMatch.setOpName(opName);
                curMatch.setDate(date);

                MatchFileManager.saveMatch(this, curMatch);
                //ourTeam.getMatchHistory().add(curMatch);

                refreshFragment(FragmentList.CURRENTMATCH);
            }
        }
        else if (requestCode == 3) {
            //Enter Player
            if (resultCode == RESULT_OK && data != null) {
                Player player = (Player) data.getSerializableExtra("newPlayer");
                Toast.makeText(this, player.getPosition().toString(), Toast.LENGTH_SHORT).show();
                refreshFragment(FragmentList.BATTERLIST);
            }
        }
    }
}