package com.harry.yaguban;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.harry.yaguban.dummy.FragmentList;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class ManagePlayerFragment extends Fragment {

    private ArrayList<Player> playerList;

    MainActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();

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

        PlayerAdapter playerAdapter = new PlayerAdapter();
        recyclerViewPlayerList.setAdapter(playerAdapter);
        recyclerViewPlayerList.setItemAnimator(new DefaultItemAnimator());
        buttonAddPlayer = view.findViewById(R.id.buttonAddPlayer);

        playerAdapter.addPlayer(new Player("김세영", 12, Position.FIRST));

        //add player button listener
        buttonAddPlayer.setOnClickListener(v -> {
            //show popup
        });

        // Inflate the layout for this fragment
        return view;
    }
}