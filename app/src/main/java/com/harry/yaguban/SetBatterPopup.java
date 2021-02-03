package com.harry.yaguban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class SetBatterPopup extends Activity {
    Button plusHit, minusHit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_batter_popup);
    }
}