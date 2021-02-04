package com.harry.yaguban;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.harry.yaguban.dummy.FragmentList;

public class SetBatterPopup extends AppCompatActivity implements View.OnClickListener {
    Button plusTimesAtBat, minusTimesAtBat;
    Button plusHit1, minusHit1;
    Button plusHit2, minusHit2;
    Button plusHit3, minusHit3;
    Button plusHomerun, minusHomerun;
    Button plusStrikeOut, minusStrikeOut;
    Button plusBall4, minusBall4;
    Button plusSacrificeFly, minusSacrificeFly;
    Player p;
    TextView name;
    TextView timesAtBat;
    TextView hit1, hit2, hit3, homerun, strikeOut, ball4, sacrificeFly;
    TextView cancel, confirm;
    int position;
    Match curMatch;

    private void attachView() {
        plusTimesAtBat = findViewById(R.id.button_plus_times_at_bat);
        minusTimesAtBat = findViewById(R.id.button_minus_times_at_bat);
        plusHit1 = findViewById(R.id.button_plus_hit1);
        minusHit1 = findViewById(R.id.button_minus_hit1);
        plusHit2 = findViewById(R.id.button_plus_hit2);
        minusHit2 = findViewById(R.id.button_minus_hit2);
        plusHit3 = findViewById(R.id.button_plus_hit3);
        minusHit3 = findViewById(R.id.button_minus_hit3);
        plusHomerun = findViewById(R.id.button_plus_homerun);
        minusHomerun = findViewById(R.id.button_minus_homerun);
        plusStrikeOut = findViewById(R.id.button_plus_strike_out);
        minusStrikeOut = findViewById(R.id.button_minus_strike_out);
        plusBall4 = findViewById(R.id.button_plus_ball4);
        minusBall4 = findViewById(R.id.button_minus_ball4);
        plusSacrificeFly = findViewById(R.id.button_plus_sacrifice_fly);
        minusSacrificeFly = findViewById(R.id.button_minus_sacrifice_fly);

        name = findViewById(R.id.textView_player_name);
        timesAtBat = findViewById(R.id.textView_times_at_bat);
        hit1 = findViewById(R.id.textView_hit1);
        hit2 = findViewById(R.id.textView_hit2);
        hit3 = findViewById(R.id.textView_hit3);
        homerun = findViewById(R.id.textView_homerun);
        strikeOut = findViewById(R.id.textView_strike_out);
        ball4 = findViewById(R.id.textView_ball4);
        sacrificeFly = findViewById(R.id.textView_sacrifice_fly);
        confirm = findViewById(R.id.textView_confirm);
        cancel = findViewById(R.id.textView_cancel);

        plusTimesAtBat.setOnClickListener(this);
        minusTimesAtBat.setOnClickListener(this);
        plusHit1.setOnClickListener(this);
        minusHit1.setOnClickListener(this);
        plusHit2.setOnClickListener(this);
        minusHit2.setOnClickListener(this);
        plusHit3.setOnClickListener(this);
        minusHit3.setOnClickListener(this);
        plusHomerun.setOnClickListener(this);
        minusHomerun.setOnClickListener(this);
        plusStrikeOut.setOnClickListener(this);
        minusStrikeOut.setOnClickListener(this);
        plusBall4.setOnClickListener(this);
        minusBall4.setOnClickListener(this);
        plusSacrificeFly.setOnClickListener(this);
        minusSacrificeFly.setOnClickListener(this);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void initSetText() {
        name.setText(p.getName());
        timesAtBat.setText(String.valueOf(p.getTimesAtBat()));
        hit1.setText(String.valueOf(p.getHit1()));
        hit2.setText(String.valueOf(p.getHit2()));
        hit3.setText(String.valueOf(p.getHit3()));
        homerun.setText(String.valueOf(p.getHomerun()));
        strikeOut.setText(String.valueOf(p.getStrikeOut()));
        ball4.setText(String.valueOf(p.getBall4()));
        sacrificeFly.setText(String.valueOf(p.getSacrificeFly()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_batter_popup);

        curMatch = MatchFileManager.loadMatch(this);
        Intent intent = getIntent();
        p = (Player) intent.getSerializableExtra("batter");
        position = intent.getIntExtra("position", 0);

        attachView();
        initSetText();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_plus_times_at_bat) {
            p.increaseTimesAtBat();
            timesAtBat.setText(String.valueOf(p.getTimesAtBat()));
        } else if (v.getId() == R.id.button_minus_times_at_bat) {
            p.decreaseTimesAtBat();
            timesAtBat.setText(String.valueOf(p.getTimesAtBat()));
        } else if (v.getId() == R.id.button_plus_hit1) {
            p.increaseHit1();
            hit1.setText(String.valueOf(p.getHit1()));
        } else if (v.getId() == R.id.button_minus_hit1) {
            p.decreaseHit1();
            hit1.setText(String.valueOf(p.getHit1()));
        } else if (v.getId() == R.id.button_plus_hit2) {
            p.increaseHit2();
            hit2.setText(String.valueOf(p.getHit2()));
        } else if (v.getId() == R.id.button_minus_hit2) {
            p.decreaseHit2();
            hit2.setText(String.valueOf(p.getHit2()));
        } else if (v.getId() == R.id.button_plus_hit3) {
            p.increaseHit3();
            hit3.setText(String.valueOf(p.getHit3()));
        } else if (v.getId() == R.id.button_minus_hit3) {
            p.decreaseHit3();
            hit3.setText(String.valueOf(p.getHit3()));
        } else if (v.getId() == R.id.button_plus_homerun) {
            p.increaseHomerun();
            homerun.setText(String.valueOf(p.getHomerun()));
        } else if (v.getId() == R.id.button_minus_homerun) {
            p.decreaseHomerun();
            homerun.setText(String.valueOf(p.getHomerun()));
        } else if (v.getId() == R.id.button_plus_strike_out) {
            p.increaseStrikeOut();
            strikeOut.setText(String.valueOf(p.getStrikeOut()));
        } else if (v.getId() == R.id.button_minus_strike_out) {
            p.decreaseStrikeOut();
            strikeOut.setText(String.valueOf(p.getStrikeOut()));
        } else if (v.getId() == R.id.button_plus_ball4) {
            p.increaseBall4();
            ball4.setText(String.valueOf(p.getBall4()));
        } else if (v.getId() == R.id.button_minus_ball4) {
            p.decreaseBall4();
            ball4.setText(String.valueOf(p.getBall4()));
        } else if (v.getId() == R.id.button_plus_sacrifice_fly) {
            p.increaseSacrificeFly();
            sacrificeFly.setText(String.valueOf(p.getSacrificeFly()));
        } else if (v.getId() == R.id.button_minus_sacrifice_fly) {
            p.decreaseSacrificeFly();
            sacrificeFly.setText(String.valueOf(p.getSacrificeFly()));
        } else if (v.getId() == R.id.textView_confirm) {
            curMatch.getBatterList().set(position, this.p);

            MatchFileManager.saveMatch(this, curMatch);

            finish();
        } else if (v.getId() == R.id.textView_cancel) {
            finish();
        }
    }
}