package com.harry.yaguban;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PitcherListFragment extends Fragment {
    Team ourTeam = new Team();
    Match curMatch = new Match(getActivity());
    MainActivity activity;
    FloatingActionButton enterPlayerButton;
    RecyclerView pitcherListRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("투수");

        ourTeam = TeamFileManager.loadTeam(getActivity());
        if (ourTeam.getPlayerList().isEmpty()) {
            return inflater.inflate(R.layout.no_player_layout, container, false);
        }
        View view = inflater.inflate(R.layout.fragment_pitcher_list, container, false);

        curMatch = MatchFileManager.loadMatch(getActivity());
        if (curMatch == null) {
            view = inflater.inflate(R.layout.no_match_list_layout, container, false);
            TextView text = view.findViewById(R.id.textView_no_current_match);
            text.setText("진행중인 경기가 없습니다.\n\n경기를 추가해주세요!");

            return view;
        }

        activity = (MainActivity)getActivity();

        pitcherListRecycler = view.findViewById(R.id.recycler_pitcher_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        pitcherListRecycler.setLayoutManager(layoutManager);
        pitcherListRecycler.scrollToPosition(0);
        pitcherListRecycler.setHasFixedSize(true);

        PitcherListAdapter adapter = new PitcherListAdapter(curMatch);
        adapter.setActivity((MainActivity) getActivity());
        pitcherListRecycler.setAdapter(adapter);
        pitcherListRecycler.setItemAnimator(new DefaultItemAnimator());

        enterPlayerButton = (FloatingActionButton) view.findViewById(R.id.button_add_pitcher);
        enterPlayerButton.setOnClickListener(v -> {
            curMatch = MatchFileManager.loadMatch(getActivity());
            activity.launchEnterPlayerPopup(false);
        });

        return view;
    }
}