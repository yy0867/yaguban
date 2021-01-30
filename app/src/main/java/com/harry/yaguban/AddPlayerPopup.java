package com.harry.yaguban;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.harry.yaguban.dummy.FragmentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddPlayerPopup extends DialogFragment {

    MainActivity activity;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_add_player_popup, container, false);

        Spinner selectPlayerPosition = view.findViewById(R.id.spinner_select_position);
        EditText inputPlayerName = view.findViewById(R.id.edit_text_player_name);
        String playerName = inputPlayerName.getText().toString();

        ArrayList<String> positionList = Position.getPositionList();
        //ArrayAdapter<String> positionItems = new ArrayAdapter<>(getActivity(), R.layout.fragment_add_player_popup, positionList);

        //selectPlayerPosition.setAdapter(positionItems);

        //add player button
        Button addPlayerButton = view.findViewById(R.id.button_add_player_complete);

        if (playerName.isEmpty()) {
            addPlayerButton.setText("이름을 입력해주세요");
            addPlayerButton.setBackgroundColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.gray));
        } else {
            addPlayerButton.setText("선수를 추가합니다");
            addPlayerButton.setBackgroundColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.main_color));
        }

        //add player button
        addPlayerButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("playerName", playerName);

            activity.saveFragmentData(bundle);
            activity.changeFragment(FragmentList.MANAGEPLAYER);
        });

        return view;
    }
}