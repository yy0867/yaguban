package com.harry.yaguban;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class CurrentMatchFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("현재 경기");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_match, container, false);
    }
}