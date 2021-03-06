package com.harry.yaguban;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class PlayerListTitleAdapter extends RecyclerView.Adapter<PlayerListTitleAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTitle;
        TextView backNumberTitle;
        TextView positionTitle;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setBackgroundColor(itemView.getResources().getColor(R.color.background_color));
            nameTitle = itemView.findViewById(R.id.recycler_player_name);
            backNumberTitle = itemView.findViewById(R.id.recycler_player_backnumber);
            positionTitle = itemView.findViewById(R.id.recycler_player_position);
            checkBox = itemView.findViewById(R.id.checkbox_delete_player);
        }

        public void setItem() {
            nameTitle.setText("이름");
            nameTitle.setTextSize(16);
            nameTitle.setTextColor(Color.BLACK);
            backNumberTitle.setText("등번호");
            backNumberTitle.setTextSize(16);
            backNumberTitle.setTextColor(Color.BLACK);
            positionTitle.setText("주 포지션");
            positionTitle.setTextSize(16);
            positionTitle.setTextColor(Color.BLACK);
            checkBox.setVisibility(View.GONE);
        }
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
        holder.setItem();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
