package com.harry.yaguban;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.ArrayList;
import java.util.Objects;

public class ManagePlayerFragment extends Fragment {

    Team ourTeam = new Team();
    MainActivity activity;
    PlayerAdapter playerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        ourTeam = TeamFileManager.loadTeam(Objects.requireNonNull(getActivity()));

        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("선수 관리");
        Button buttonAddPlayer;

        View view = inflater.inflate(R.layout.fragment_manage_player, container, false);
        RecyclerView recyclerViewPlayerList = view.findViewById(R.id.recyclerview_player_list);
        RecyclerView recyclerViewPlayerTitle = view.findViewById(R.id.recyclerview_player_title);
        recyclerViewPlayerList.setHasFixedSize(true);
        recyclerViewPlayerTitle.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewPlayerList.setLayoutManager(layoutManager);
        recyclerViewPlayerList.scrollToPosition(0);

        RecyclerView.LayoutManager layoutTitleManager = new LinearLayoutManager(getActivity());
        recyclerViewPlayerTitle.setLayoutManager(layoutTitleManager);

        PlayerListTitleAdapter titleAdapter = new PlayerListTitleAdapter();
        recyclerViewPlayerTitle.setAdapter(titleAdapter);

        playerAdapter = new PlayerAdapter();
        recyclerViewPlayerList.setAdapter(playerAdapter);
        recyclerViewPlayerList.setItemAnimator(new DefaultItemAnimator());
        buttonAddPlayer = view.findViewById(R.id.buttonAddPlayer);

        //add player button listener
        buttonAddPlayer.setOnClickListener(v -> {
            //show popup
            activity.launchAddPlayerPopup();
        });

        showListToView();

        // Inflate the layout for this fragment
        return view;
    }

    private void showListToView() {
        if (ourTeam != null) {
            for (Player p : ourTeam.getPlayerList()) {
                playerAdapter.addPlayer(p);
            }
        }
    }
}