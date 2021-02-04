package com.harry.yaguban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMatchPopupActivity extends AppCompatActivity {

    EditText opTeamName;
    EditText matchLocation;
    EditText matchDate;
    Button addMatchButton;

    String team = "";
    String location = "";
    String date = "";
    boolean isFormComplete = false;

    Calendar calendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener datePicker = (view, year, month, dayOfMonth) -> {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateText();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match_popup);

        opTeamName = findViewById(R.id.edit_text_op_team_name);
        matchLocation = findViewById(R.id.edit_text_match_location);
        matchDate = findViewById(R.id.edit_text_match_date);
        addMatchButton = findViewById(R.id.button_add_match_complete);

        opTeamName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {manageButtonActivation(); }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) team = "";
                else team = s.toString();
                manageButtonActivation();
            }

            @Override
            public void afterTextChanged(Editable s) {manageButtonActivation(); }
        });
        matchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                manageButtonActivation();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) location = "";
                else location = s.toString();
                manageButtonActivation();
            }

            @Override
            public void afterTextChanged(Editable s) {
                manageButtonActivation();
            }
        });
        matchDate.setOnClickListener(v -> {
            new DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            updateText();
            manageButtonActivation();
        });

        manageButtonActivation();
    }

    public void addMatchClicked(View v) {
        Intent intent = new Intent();

        intent.putExtra("team", team);
        intent.putExtra("location", location);
        intent.putExtra("date", date);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void manageButtonActivation() {
        addMatchButton.setBackgroundColor(getResources().getColor(R.color.main_color));
        if (team.isEmpty()) {
            addMatchButton.setBackgroundColor(getResources().getColor(R.color.gray));
            isFormComplete = false;
            addMatchButton.setClickable(false);
            addMatchButton.setText(getResources().getString(R.string.team_name_hint));
        }
        else if (location.isEmpty()) {
            addMatchButton.setBackgroundColor(getResources().getColor(R.color.gray));
            isFormComplete = false;
            addMatchButton.setClickable(false);
            addMatchButton.setText(getResources().getString(R.string.match_locate_hint));
        }
        else if (date.isEmpty()) {
            addMatchButton.setBackgroundColor(getResources().getColor(R.color.gray));
            isFormComplete = false;
            addMatchButton.setClickable(false);
            addMatchButton.setText(getResources().getString(R.string.match_date_hint));
        } else {
            isFormComplete = true;
            addMatchButton.setClickable(true);
            addMatchButton.setBackgroundColor(getResources().getColor(R.color.main_color));
            addMatchButton.setTextColor(getResources().getColor(R.color.white));
            addMatchButton.setText(getResources().getString(R.string.add_match_complete));
        }
    }

    private void updateText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);

        date = dateFormat.format(calendar.getTime());
        matchDate.setText(date);
    }
}