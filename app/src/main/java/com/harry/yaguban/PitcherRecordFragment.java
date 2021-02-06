package com.harry.yaguban;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PitcherRecordFragment extends Fragment {

    PitcherListAdapter pitcherAdapter;
    RecyclerView pitcherRecycler;
    Match curMatch;

    PitcherRecordFragment(Match selectedMatch) {
        curMatch = selectedMatch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pitcher_record, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        pitcherRecycler = view.findViewById(R.id.recycler_pitcher_record);
        pitcherRecycler.setLayoutManager(layoutManager);
        pitcherRecycler.scrollToPosition(0);
        pitcherRecycler.setHasFixedSize(true);

        pitcherAdapter = new PitcherListAdapter(curMatch);
        pitcherAdapter.setVisibility(false);
        pitcherRecycler.setAdapter(pitcherAdapter);
        pitcherRecycler.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}