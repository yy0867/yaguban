package com.harry.yaguban;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PitcherListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("투수");

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pitcher_list, container, false);
        // If No Player Exist, run code under
        return inflater.inflate(R.layout.no_player_layout, container, false);
    }
}