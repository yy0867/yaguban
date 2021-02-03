package com.harry.yaguban;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

public class BatterListFragment extends Fragment {
    Team ourTeam = new Team();
    Match curMatch = new Match(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        actionBar.setTitle("타자");
        View view = inflater.inflate(R.layout.fragment_batter_list, container, false);

        ourTeam = TeamFileManager.loadTeam(getActivity());
        if (ourTeam.getPlayerList().isEmpty())
            return inflater.inflate(R.layout.no_player_layout, container, false);

        curMatch = MatchFileManager.loadMatch(getActivity());
        if (curMatch == null || curMatch.getOpName().isEmpty()) {
            view = inflater.inflate(R.layout.no_match_list_layout, container, false);
            TextView text = view.findViewById(R.id.textView_no_current_match);
            text.setText("진행중인 경기가 없습니다.\n\n경기를 추가해주세요!");
        }

        return view;
    }
}