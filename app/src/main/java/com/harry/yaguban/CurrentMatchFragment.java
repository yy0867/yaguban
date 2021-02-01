package com.harry.yaguban;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class CurrentMatchFragment extends Fragment implements View.OnClickListener {
    MainActivity mainActivity;

    Team team;
    Match match;
    Button addMatch;

    TextView opName, location;
    TextView opScoreText, ourScoreText;
    ImageButton plusOurScore, minusOurScore;
    ImageButton plusOpScore, minusOpScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("현재 경기");
        mainActivity = (MainActivity) getActivity();

        match = MatchFileManager.loadMatch(getContext());
        team = TeamFileManager.loadTeam(getContext());

        if (match == null) {
            View view = inflater.inflate(R.layout.fragment_no_match, container, false);
            addMatch = view.findViewById(R.id.button_add_match);

            addMatch.setOnClickListener(v -> {
                if (team.getPlayerList().isEmpty()) {
                    Toast.makeText(getContext(), "선수를 먼저 추가해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                mainActivity.launchAddMatchPopup();
            });

            return view;
        }

        team = TeamFileManager.loadTeam(getContext());
        View view = inflater.inflate(R.layout.fragment_current_match, container, false);

        opName = view.findViewById(R.id.textView_op_name);
        location = view.findViewById(R.id.textView_match_location);
        opScoreText = view.findViewById(R.id.textView_op_score);
        ourScoreText = view.findViewById(R.id.textView_our_score);
        plusOpScore = view.findViewById(R.id.button_op_score_plus);
        minusOpScore = view.findViewById(R.id.button_op_score_minus);
        plusOurScore = view.findViewById(R.id.button_our_score_plus);
        minusOurScore = view.findViewById(R.id.button_our_score_minus);

        opName.setText(match.getOpName());
        location.setText(match.getLocation());
        opScoreText.setText("0");
        ourScoreText.setText("0");

        // Inflate the layout for this fragment
        return view;
    }

    private void plusOurScoreClicked() {
        match.increaseOurScore();
        ourScoreText.setText(String.valueOf(match.getOurScore()));

        MatchFileManager.saveMatch(getContext(), match);
    }

    private void minusOurScoreClicked() {
        match.decreaseOurScore();
        ourScoreText.setText(String.valueOf(match.getOurScore()));

        MatchFileManager.saveMatch(getContext(), match);
    }

    private void plusOpScoreClicked() {
        match.increaseOpScore();
        opScoreText.setText(String.valueOf(match.getOpScore()));

        MatchFileManager.saveMatch(getContext(), match);
    }

    private void minusOpScoreClicked() {
        match.decreaseOpScore();
        opScoreText.setText(String.valueOf(match.getOpScore()));

        MatchFileManager.saveMatch(getContext(), match);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_our_score_plus) {
            plusOurScoreClicked();
        } else if (v.getId() == R.id.button_our_score_minus) {
            minusOurScoreClicked();
        } else if (v.getId() == R.id.button_op_score_plus) {
            plusOpScoreClicked();
        } else if (v.getId() == R.id.button_op_score_minus) {
            minusOpScoreClicked();
        }
    }
}