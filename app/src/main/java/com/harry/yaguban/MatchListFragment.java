package com.harry.yaguban;

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

import java.util.Objects;

public class MatchListFragment extends Fragment {
    Team team;
    RecyclerView matchListRecycler;
    MatchListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("경기 기록");
        team = TeamFileManager.loadTeam(getActivity());

        if (team.getMatchHistory().isEmpty()) {
            return inflater.inflate(R.layout.no_match_list_layout, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_match_list, container, false);
        matchListRecycler = view.findViewById(R.id.recyclerview_match_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        matchListRecycler.setLayoutManager(layoutManager);
        matchListRecycler.scrollToPosition(0);
        matchListRecycler.setHasFixedSize(true);

        adapter = new MatchListAdapter(team);
        matchListRecycler.setAdapter(adapter);
        matchListRecycler.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}