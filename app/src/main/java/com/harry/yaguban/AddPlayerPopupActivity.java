package com.harry.yaguban;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPlayerPopupActivity extends AppCompatActivity {

    Position playerPosition;
    String playerName = "";
    int playerBackNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player_popup);

        Spinner selectPosition = findViewById(R.id.spinner_select_position);
        EditText inputPlayerName = findViewById(R.id.edit_text_player_name);
        EditText inputBackNumber = findViewById(R.id.edit_text_player_backnumber);
        Button buttonAddPlayer = findViewById(R.id.button_add_player_complete);

        //EditText Code
        inputPlayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    buttonAddPlayer.setBackgroundColor(getResources().getColor(R.color.gray));
                    buttonAddPlayer.setText("이름을 입력해주세요");
                } else if (playerBackNumber == 0) {
                    buttonAddPlayer.setBackgroundColor(getResources().getColor(R.color.gray));
                    buttonAddPlayer.setText("등번호를 입력해주세요");
                } else {
                    buttonAddPlayer.setBackgroundColor(getResources().getColor(R.color.main_color));
                    buttonAddPlayer.setText("선수를 추가합니다!");
                }
                playerName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        inputBackNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    buttonAddPlayer.setBackgroundColor(getResources().getColor(R.color.gray));
                    buttonAddPlayer.setText("등번호를 입력해주세요");
                } else if (playerName.isEmpty()) {
                    buttonAddPlayer.setBackgroundColor(getResources().getColor(R.color.gray));
                    buttonAddPlayer.setText("이름을 입력해주세요");
                    playerBackNumber = Integer.parseInt(s.toString());
                } else {
                    buttonAddPlayer.setBackgroundColor(getResources().getColor(R.color.main_color));
                    buttonAddPlayer.setText("선수를 추가합니다!");
                    playerBackNumber = Integer.parseInt(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        //Spinner Code
        ArrayList<String> positionList = Position.getPositionListToString();
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positionList);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPosition.setAdapter(positionAdapter);

        selectPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                playerPosition = Position.getPositionByNumber(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void addPlayerButtonClicked(View v) {
        if (playerName.length() > 0 && playerBackNumber > 0) {
            Intent intent = new Intent();
            intent.putExtra("playerName", playerName);
            intent.putExtra("playerPosition", playerPosition);
            intent.putExtra("playerBackNumber", playerBackNumber);

            setResult(RESULT_OK, intent);

            finish();
        }
    }
}