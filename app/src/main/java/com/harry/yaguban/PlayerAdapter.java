package com.harry.yaguban;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    //ArrayList<Player> playerList = new ArrayList<>();
    Team team = new Team();
    int visibility = View.GONE;
    float name = 0.1f, backNumber = 0.4f, position = 0.65f;

    static class ViewHolder extends RecyclerView.ViewHolder {
        Team team;
        TextView playerName;
        TextView playerBackNumber;
        TextView playerPosition;
        public CheckBox checkboxRemovePlayer;
        Guideline lineName;
        Guideline lineBackNumber;
        Guideline linePosition;

        public ViewHolder(View itemView) {
            super(itemView);

            checkboxRemovePlayer = itemView.findViewById(R.id.checkbox_delete_player);
            playerName = itemView.findViewById(R.id.recycler_player_name);
            playerBackNumber = itemView.findViewById(R.id.recycler_player_backnumber);
            playerPosition = itemView.findViewById(R.id.recycler_player_position);
            lineName = itemView.findViewById(R.id.guideline_name);
            lineBackNumber = itemView.findViewById(R.id.guideline_backnumber);
            linePosition = itemView.findViewById(R.id.guideline_position);
            team = TeamFileManager.loadTeam(itemView.getContext());
        }

        public void setItem(Player player) {
            playerName.setText(player.getName());
            playerBackNumber.setText(String.valueOf(player.getBackNumber()));
            playerPosition.setText(player.getPosition().toString());
        }

        public void setCheckboxVisibility(int visibility) {
            checkboxRemovePlayer.setVisibility(visibility);
        }

        public void setGuideLine(float name, float backNumber, float position) {
            //guideline for name
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) lineName.getLayoutParams();
            params.guidePercent = name;
            lineName.setLayoutParams(params);

            //guideline for back number
            params = (ConstraintLayout.LayoutParams) lineBackNumber.getLayoutParams();
            params.guidePercent = backNumber;
            lineBackNumber.setLayoutParams(params);

            //guideline for position
            params = (ConstraintLayout.LayoutParams) linePosition.getLayoutParams();
            params.guidePercent = position;
            linePosition.setLayoutParams(params);
        }
    }

    public void init() { team.getPlayerList().clear(); }
    public void addPlayer(Player player) { team.getPlayerList().add(player); }

    public void setCheckboxVisibility(int visibility) {
        this.visibility = visibility;
    }
    public void setGuideLine(boolean isRemoveState) {
        name = isRemoveState ? 0.2f : 0.1f;
        backNumber = isRemoveState ? 0.5f : 0.4f;
        position = isRemoveState ? 0.7f : 0.65f;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Team removePlayer() {
        team.getPlayerList().removeIf(Player::isSelected);

        return team;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.player_list_recycler, parent, false);
        team = TeamFileManager.loadTeam(parent.getContext());

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = team.getPlayerList().get(position);
        holder.setItem(player);
        holder.setCheckboxVisibility(visibility);
        holder.setGuideLine(this.name, this.backNumber, this.position);
        holder.checkboxRemovePlayer.setChecked(team.getPlayerList().get(position).isSelected());

        holder.checkboxRemovePlayer.setOnClickListener(v -> {
            team.getPlayerList().get(position).setSelected(!team.getPlayerList().get(position).isSelected());
        });
    }

    @Override
    public int getItemCount() {
        return team.getPlayerList().size();
    }
}
