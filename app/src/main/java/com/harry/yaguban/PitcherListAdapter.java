package com.harry.yaguban;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harry.yaguban.dummy.FragmentList;

import java.util.List;
import java.util.Locale;

public class PitcherListAdapter extends RecyclerView.Adapter<PitcherListAdapter.ViewHolder> {

    List<Player> pitcherList;
    MainActivity activity;
    Match curMatch;
    Button deleteButton;
    Button minusStrikeOut, plusStrikeOut;
    Button minusBall4, plusBall4;
    Button minusHitted, plusHitted;
    Button minusLosePoint, plusLosePoint;
    Button minusInnnings, plusInnnings;
    Button minusPitchCount, plusPitchCount;
    boolean isVisible;

    PitcherListAdapter(Match match) { pitcherList = match.getPitcherList(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, strikeOutCount, ball4Count, hittedCount, era, pitchCount, losePoint, innings;
        Match curMatch;
        MainActivity activity;

        public ViewHolder(View itemView) {
            super(itemView);
            curMatch = MatchFileManager.loadMatch(itemView.getContext());
        }

        private void attachView() {
            name = itemView.findViewById(R.id.textView_player_name);
            strikeOutCount = itemView.findViewById(R.id.textView_strike_out_count);
            ball4Count = itemView.findViewById(R.id.textView_ball4_count);
            hittedCount = itemView.findViewById(R.id.textView_hitted_count);
            era = itemView.findViewById(R.id.textView_era);
            pitchCount = itemView.findViewById(R.id.textView_pitchcount);
            losePoint = itemView.findViewById(R.id.textView_lose_point_count);
            innings = itemView.findViewById(R.id.textView_innings);
        }

        public void setTexts(Player p) {
            attachView();

            name.setText(p.getName());
            strikeOutCount.setText(String.valueOf(p.getStrikeCount()));
            ball4Count.setText(String.valueOf(p.getBall4Count()));
            hittedCount.setText(String.valueOf(p.getHittedCount()));
            pitchCount.setText(String.valueOf(p.getPitchCount()));
            losePoint.setText(String.valueOf(p.getLosePoint()));
            String text = "방어율 " + String.format(Locale.getDefault(), "%.3f", p.getEra());
            era.setText(text);
            text = String.format(Locale.getDefault(), "%.1f", p.getInnings());
            innings.setText(text);
        }

        public void setActivity(MainActivity activity) { this.activity = activity; }

        public void deleteItem(int position) {
            if (curMatch.getPitcherList().size() == 0) return;
            curMatch.getPitcherList().remove(position);
            MatchFileManager.saveMatch(itemView.getContext(), curMatch);
        }
    }

    public void setActivity(MainActivity activity) { this.activity = activity; }

    public void setVisibility(boolean isVisible) { this.isVisible = isVisible; }

    public void setVisible() {
        int visibility = isVisible ? View.VISIBLE : View.INVISIBLE;
        plusStrikeOut.setVisibility(visibility);
        minusStrikeOut.setVisibility(visibility);
        plusBall4.setVisibility(visibility);
        minusBall4.setVisibility(visibility);
        plusHitted.setVisibility(visibility);
        minusHitted.setVisibility(visibility);
        plusLosePoint.setVisibility(visibility);
        minusLosePoint.setVisibility(visibility);
        plusInnnings.setVisibility(visibility);
        minusInnnings.setVisibility(visibility);
        plusPitchCount.setVisibility(visibility);
        minusPitchCount.setVisibility(visibility);
        deleteButton.setVisibility(visibility);
    }

    @NonNull
    @Override
    public PitcherListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pitcher_detail_recycler, parent, false);

        deleteButton = itemView.findViewById(R.id.button_delete_player);
        curMatch = MatchFileManager.loadMatch(itemView.getContext());

        plusStrikeOut = itemView.findViewById(R.id.button_plus_strike);
        minusStrikeOut = itemView.findViewById(R.id.button_minus_strike);
        plusBall4 = itemView.findViewById(R.id.button_plus_ball4);
        minusBall4 = itemView.findViewById(R.id.button_minus_ball4);
        plusHitted = itemView.findViewById(R.id.button_plus_hitted);
        minusHitted = itemView.findViewById(R.id.button_minus_hitted);
        plusLosePoint = itemView.findViewById(R.id.button_plus_lose_point);
        minusLosePoint = itemView.findViewById(R.id.button_minus_lose_point);
        plusInnnings = itemView.findViewById(R.id.button_plus_innings);
        minusInnnings = itemView.findViewById(R.id.button_minus_innings);
        plusPitchCount = itemView.findViewById(R.id.button_plus_pitchcount);
        minusPitchCount = itemView.findViewById(R.id.button_minus_pitchcount);

        setVisible();

        return new PitcherListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PitcherListAdapter.ViewHolder holder, int position) {
        if (isVisible) holder.setActivity(activity);
        holder.setTexts(pitcherList.get(position));

        deleteButton.setOnClickListener(v -> {
            holder.deleteItem(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        minusStrikeOut.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).decreaseStrikeCount();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        plusStrikeOut.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).increaseStrikeCount();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        minusBall4.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).decreaseBall4Count();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        plusBall4.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).increaseBall4Count();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        minusHitted.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).decreaseHittedCount();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        plusHitted.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).increaseHittedCount();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        minusLosePoint.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).decreaseLosePoint();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        plusLosePoint.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).increaseLosePoint();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        minusInnnings.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).decreaseInnings();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        plusInnnings.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).increaseInnings();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        minusPitchCount.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).decreasePitchCount();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
        plusPitchCount.setOnClickListener(v -> {
            curMatch.getPitcherList().get(position).increasePitchCount();
            notifyItemChanged(position);
            MatchFileManager.saveMatch(holder.itemView.getContext(), curMatch);
            if (isVisible) activity.refreshFragment(FragmentList.PITCHERLIST);
        });
    }

    @Override
    public int getItemCount() {
        return pitcherList.size();
    }
}