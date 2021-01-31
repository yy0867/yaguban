package com.harry.yaguban;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class BatterListFragment extends Fragment {
    Team ourTeam = new Team();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        actionBar.setTitle("타자");

        ourTeam = TeamFileManager.loadTeam(getActivity());

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_batter_list, container, false);
        // If No Player Exist, run code under
        if (ourTeam.getPlayerList().isEmpty()) {
            return inflater.inflate(R.layout.no_player_layout, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_batter_list, container, false);
        }
    }
}