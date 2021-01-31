package com.harry.yaguban;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


import com.harry.yaguban.dummy.FragmentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagePlayerFragment extends Fragment {

    Team ourTeam = new Team();
    MainActivity activity;
    CheckBox removeCheckbox;
    PlayerAdapter playerAdapter;
    RecyclerView recyclerViewPlayerList;
    RecyclerView recyclerViewPlayerTitle;
    boolean isRemoveState = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isRemoveState = false;
        activity = (MainActivity) getActivity();
        ourTeam = TeamFileManager.loadTeam(Objects.requireNonNull(getActivity()));

        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("선수 관리");
        Button buttonAddPlayer, buttonRemovePlayer;

        View view = inflater.inflate(R.layout.fragment_manage_player, container, false);
        recyclerViewPlayerList = view.findViewById(R.id.recyclerview_player_list);
        recyclerViewPlayerTitle = view.findViewById(R.id.recyclerview_player_title);
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

        buttonAddPlayer = view.findViewById(R.id.button_add_player);
        buttonRemovePlayer = view.findViewById(R.id.button_remove_player);

        //add player button listener
        buttonAddPlayer.setOnClickListener(v -> {
            //show popup
            activity.launchAddPlayerPopup();
        });

        //remove player button listener
        buttonRemovePlayer.setOnClickListener(v -> {
            if (!isRemoveState) {
                isRemoveState = true;
                buttonAddPlayer.setVisibility(View.GONE);
                changeDelete();
            } else {
                isRemoveState = false;
                buttonAddPlayer.setVisibility(View.VISIBLE);
                changeOrigin();
            }
            playerAdapter.notifyDataSetChanged();
        });

        showListToView();

        // Inflate the layout for this fragment
        return view;
    }

    private void changeDelete() {
        changeGuideLine(0.2f, 0.5f, 0.7f, View.GONE);
    }

    private void changeOrigin() {
        changeGuideLine(0.1f, 0.4f, 0.65f, View.VISIBLE);
    }

    private void changeGuideLine(float name, float backNumber, float position, int visibility) {
        Guideline lineName = recyclerViewPlayerList.findViewById(R.id.guideline_name);
        Guideline lineBackNumber = recyclerViewPlayerList.findViewById(R.id.guideline_backnumber);
        Guideline linePosition = recyclerViewPlayerList.findViewById(R.id.guideline_position);

        recyclerViewPlayerTitle.setVisibility(visibility);
        playerAdapter.setCheckboxVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);

        //guideline for name
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) lineName.getLayoutParams();
        params.guidePercent = name;
        lineName.setLayoutParams(params);

        //guideline for back number
        params = (ConstraintLayout.LayoutParams) lineBackNumber.getLayoutParams();
        params.guidePercent = backNumber;
        lineBackNumber.setLayoutParams(params);

        //guideline for position
        params = (ConstraintLayout.LayoutParams) linePosition.getLayoutParams();
        params.guidePercent = position;
        linePosition.setLayoutParams(params);
    }

    private void showListToView() {
        if (ourTeam != null) {
            playerAdapter.init();
            for (Player p : ourTeam.getPlayerList()) {
                playerAdapter.addPlayer(p);
            }
        }
    }
}