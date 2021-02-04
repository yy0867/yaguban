package com.harry.yaguban;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harry.yaguban.dummy.FragmentList;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class BatterListAdapter extends RecyclerView.Adapter<BatterListAdapter.ViewHolder> {

    List<Player> batterList;
    Match curMatch;
    Button deleteButton;
    MainActivity activity;

    BatterListAdapter(Match match) {
        batterList = match.getBatterList();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, position, battingAvg, timesAtBat, hit;
        TextView strikeOut, ball4, sacrificeFly, basePercent, sluggingAvg;
        Match curMatch;
        MainActivity activity;

        public ViewHolder(View itemView) {
            super(itemView);
            curMatch = MatchFileManager.loadMatch(itemView.getContext());

            itemView.setOnClickListener(v -> {
                activity.launchSetBatterPopup(getAdapterPosition());
                curMatch = MatchFileManager.loadMatch(itemView.getContext());

                setTexts(curMatch.getBatterList().get(getAdapterPosition()));
                activity.refreshFragment(FragmentList.BATTERLIST);
            });
        }

        private void attachViewById() {
            name = itemView.findViewById(R.id.textView_player_name);
            position = itemView.findViewById(R.id.textView_player_position);
            battingAvg = itemView.findViewById(R.id.textView_batting_avg);
            timesAtBat = itemView.findViewById(R.id.textView_times_at_bat);
            hit = itemView.findViewById(R.id.textView_hit);
            strikeOut = itemView.findViewById(R.id.textView_strike_out);
            ball4 = itemView.findViewById(R.id.textView_ball4);
            sacrificeFly = itemView.findViewById(R.id.textView_sacrifice_fly);
            basePercent = itemView.findViewById(R.id.textView_base_percentage);
            sluggingAvg = itemView.findViewById(R.id.textView_slugging_avg);
        }

        public void setActivity(MainActivity activity) { this.activity = activity; }

        public void setTexts(Player p) {
            attachViewById();

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

        public void deleteItem(int position) {
            if (curMatch.getBatterList().size() == 0) return;
            curMatch.getBatterList().remove(position);
            MatchFileManager.saveMatch(itemView.getContext(), curMatch);
        }
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public BatterListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.batter_detail_recycler, parent, false);
        curMatch = MatchFileManager.loadMatch(itemView.getContext());
        deleteButton = itemView.findViewById(R.id.button_delete_player);

        return new BatterListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BatterListAdapter.ViewHolder holder, int position) {
        Player p = batterList.get(position);
        holder.setTexts(p);
        holder.setActivity(activity);

        deleteButton.setOnClickListener(v -> {
            holder.deleteItem(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
            activity.refreshFragment(FragmentList.BATTERLIST);
        });
    }

    @Override
    public int getItemCount() {
        return batterList.size();
    }
}
