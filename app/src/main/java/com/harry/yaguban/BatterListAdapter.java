package com.harry.yaguban;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class BatterListAdapter extends RecyclerView.Adapter<BatterListAdapter.ViewHolder> {

    Match curMatch;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, position, battingAvg, timesAtBat, hit;
        TextView strikeOut, ball4, sacrificeFly, basePercent, sluggingAvg;
        Button deleteButton;
        Match curMatch;

        public ViewHolder(View itemView) {
            super(itemView);
            curMatch = MatchFileManager.loadMatch(itemView.getContext());
            attachViewById();
        }

        public void setItem() {
        }

        private void attachViewById() {
            name = itemView.findViewById(R.id.textView_player_name);
            position = itemView.findViewById(R.id.textView_player_position);
            battingAvg = itemView.findViewById(R.id.textView_batting_avg);
            timesAtBat = itemView.findViewById(R.id.textView_times_at_bat);
            hit = itemView.findViewById(R.id.textView_times_at_bat);
            strikeOut = itemView.findViewById(R.id.textView_strike_out);
            ball4 = itemView.findViewById(R.id.textView_ball4);
            sacrificeFly = itemView.findViewById(R.id.textView_sacrifice_fly);
            basePercent = itemView.findViewById(R.id.textView_base_percentage);
            sluggingAvg = itemView.findViewById(R.id.textView_slugging_avg);
            deleteButton = itemView.findViewById(R.id.button_delete_player);
        }

        public void setTexts(Player p) {
            name.setText(p.getName());
            position.setText(p.getPosition().toString());
            String text = "타율 " + String.format(Locale.getDefault(), "%.3f", p.getBattingAvg());
            battingAvg.setText(text);
            text = "타수 " + p.getTimesAtBat();
            timesAtBat.setText(text);
            text = "안타 " + p.getHit();
            hit.setText(text);
            text = "삼진 " + p.getStrikeOut();
            strikeOut.setText(text);
            text = "볼넷 " + p.getBall4();
            ball4.setText(text);
            text = "희생플라이 " + p.getSacrificeFly();
            sacrificeFly.setText(text);
            text = "출루율 " + String.format(Locale.getDefault(), "%.3f", p.getBasePercent());
            basePercent.setText(text);
            text = "장타율 " + String.format(Locale.getDefault(), "%.3f", p.getSluggingAvg());
            sluggingAvg.setText(text);
        }
    }

    @NonNull
    @Override
    public BatterListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.player_list_recycler, parent, false);
        MatchFileManager.loadMatch(itemView.getContext());

        return new BatterListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BatterListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return curMatch.getBatterList().size();
    }
}
