package com.harry.yaguban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MatchRecordPopup extends AppCompatActivity {

    Fragment fragmentBatterRecord;
    Fragment fragmentPitcherRecord;
    Match curMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_record_popup);

        Intent intent = getIntent();
        curMatch = (Match) intent.getSerializableExtra("match");

        setTitle(curMatch.getOpName());

        fragmentBatterRecord = new BatterRecordFragment(curMatch);
        fragmentPitcherRecord = new PitcherRecordFragment(curMatch);
        changeView(0);

        TabLayout layout = findViewById(R.id.layout_batter_pitcher_info);

        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void changeView(int pos) {
        if (pos == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_record_fragment_container, fragmentBatterRecord)
                    .commit();
        } else if (pos == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_record_fragment_container, fragmentPitcherRecord)
                    .commit();
        }
    }
}