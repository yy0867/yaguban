package com.harry.yaguban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MatchRecordPopup extends AppCompatActivity {

    TabItem batterTab, pitcherTab;
    BatterListAdapter batterAdapter;
    PitcherListAdapter pitcherAdapter;
    RecyclerView batterRecycler, pitcherRecycler;
    Match curMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_record_popup);

        Intent intent = getIntent();
        curMatch = (Match) intent.getSerializableExtra("match");

        TabLayout layout = findViewById(R.id.layout_batter_pitcher_info);
        batterTab = findViewById(R.id.tabItem_batter);
        pitcherTab = findViewById(R.id.tabItem_pitcher);

        batterRecycler = findViewById(R.id.recycler_batter_info);
        pitcherRecycler = findViewById(R.id.recycler_pitcher_info);


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
            batterTab.setVisibility(View.VISIBLE);
            pitcherTab.setVisibility(View.INVISIBLE);

            batterAdapter = new BatterListAdapter(curMatch);
            batterAdapter.setActivity((MainActivity) getApplicationContext());
            batterAdapter.setClickable(false);
            batterRecycler.setAdapter(batterAdapter);
            batterRecycler.setItemAnimator(new DefaultItemAnimator());
        } else if (pos == 1) {
            batterTab.setVisibility(View.INVISIBLE);
            pitcherTab.setVisibility(View.VISIBLE);

            pitcherAdapter = new PitcherListAdapter(curMatch);
            pitcherAdapter.setActivity((MainActivity) getApplicationContext());
            //set all buttons invisible (within pitcherListAdapter);
        }
    }
}