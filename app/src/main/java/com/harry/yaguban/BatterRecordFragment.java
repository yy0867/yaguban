package com.harry.yaguban;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BatterRecordFragment extends Fragment {

    BatterListAdapter batterAdapter;
    RecyclerView batterRecycler;
    Match curMatch;

    BatterRecordFragment(Match selectedMatch) {
        curMatch = selectedMatch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_batter_record, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        batterRecycler = view.findViewById(R.id.recycler_batter_record);
        batterRecycler.setLayoutManager(layoutManager);
        batterRecycler.scrollToPosition(0);
        batterRecycler.setHasFixedSize(true);

        batterAdapter = new BatterListAdapter(curMatch);
        batterAdapter.setClickable(false);
        batterRecycler.setAdapter(batterAdapter);
        batterRecycler.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}