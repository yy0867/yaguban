package com.harry.yaguban;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    ArrayList<Player> playerList = new ArrayList<>();
    int visibility = View.GONE;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        TextView playerBackNumber;
        TextView playerPosition;
        CheckBox checkboxRemovePlayer;

        public ViewHolder(View itemView) {
            super(itemView);

            checkboxRemovePlayer = itemView.findViewById(R.id.checkbox_delete_player);
            playerName = itemView.findViewById(R.id.recycler_player_name);
            playerBackNumber = itemView.findViewById(R.id.recycler_player_backnumber);
            playerPosition = itemView.findViewById(R.id.recycler_player_position);
        }

        public void setItem(Player player) {
            playerName.setText(player.getName());
            playerBackNumber.setText(String.valueOf(player.getBackNumber()));
            playerPosition.setText(player.getPosition().toString());
        }

        public void setCheckboxVisibility(int visibility) {
            checkboxRemovePlayer.setVisibility(visibility);
        }
    }

    public void init() { playerList.clear(); }
    public void addPlayer(Player player) { playerList.add(player); }
    public void setPlayerList(ArrayList<Player> playerList) { this.playerList = playerList; }
    public Player getPlayer(int position) { return playerList.get(position); }
    public void setPlayer(int position, Player player) { playerList.set(position, player); }

    public void setCheckboxVisibility(int visibility) {
        this.visibility = visibility;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.player_list_recycler, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.setItem(player);
        holder.setCheckboxVisibility(visibility);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}
