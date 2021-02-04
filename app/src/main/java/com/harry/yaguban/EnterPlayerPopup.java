package com.harry.yaguban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EnterPlayerPopup extends AppCompatActivity {

    Match match;
    Team team;
    List<String> playerList = new ArrayList<>();
    List<String> positionList = new ArrayList<>();
    Player selectedPlayer;
    Position selectedPosition;
    Spinner choosePosition;
    ArrayAdapter<String> positionAdapter;
    boolean isBatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_player_popup);

        match = MatchFileManager.loadMatch(this);
        team = TeamFileManager.loadTeam(this);

        Intent intent = getIntent();
        isBatter = intent.getBooleanExtra("isBatter", true);

        Spinner choosePlayer = findViewById(R.id.spinner_choose_player);
        choosePosition = findViewById(R.id.spinner_choose_position);

        playerList = team.getPlayerString();
        //removeExistPlayer();
        ArrayAdapter<String> playerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerList);

        playerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePlayer.setAdapter(playerAdapter);

        if (isBatter) {
            positionList = Position.getPositionListToString();
            positionList.remove(Position.PITCHER.toString());
        }
        else positionList.add(Position.PITCHER.toString());

        positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positionList);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePosition.setAdapter(positionAdapter);

        choosePlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPlayerName = playerList.get(position);
                selectedPlayer = new Player(team.findPlayerByName(selectedPlayerName));

                fixPositionList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        choosePosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isBatter) selectedPosition = Position.PITCHER;
                else {
                    selectedPosition = Position.getPositionByName(positionList.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fixPositionList() {
        int pos = positionList.indexOf(selectedPlayer.getPosition().toString());

        if (isBatter) {
            String temp = positionList.get(pos);
            positionList.remove(pos);
            positionList.add(0, temp);
        }

        positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positionList);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePosition.setAdapter(positionAdapter);
    }

    private void removeExistPlayer() {
        if (match.getBatterList() != null) {
            for (Player p : match.getBatterList()) {
                playerList.remove(p.getName());
            }
        }
    }

    public void enterMatchClicked(View v) {
        Player player = new Player(selectedPlayer.getName(), selectedPlayer.getBackNumber(), selectedPosition);
        Intent intent = new Intent();

        intent.putExtra("newPlayer", player);
        intent.putExtra("isBatter", isBatter);
        setResult(RESULT_OK, intent);

        finish();
    }
}