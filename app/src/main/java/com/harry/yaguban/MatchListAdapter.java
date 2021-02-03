package com.harry.yaguban;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {

    List<Match> matchList = new ArrayList<>();

    MatchListAdapter(Team team) {
        matchList = team.getMatchHistory();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Team team;
        TextView opName, score, location, result;

        public ViewHolder(View itemView) {
            super(itemView);
            team = TeamFileManager.loadTeam(itemView.getContext());
            opName = itemView.findViewById(R.id.textView_op_name);
            score = itemView.findViewById(R.id.textView_score);
            location = itemView.findViewById(R.id.textView_match_location);
            result = itemView.findViewById(R.id.textView_match_result);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    //Show Match Info
                    Toast.makeText(itemView.getContext(), team.getMatchHistory().get(position).getOpName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setItem(Match match) {

            opName.setText(match.getOpName());
            String scoreString = match.getOurScore() + " : " + match.getOpScore();
            score.setText(scoreString);
            location.setText(match.getLocation());

            if (match.getOurScore() > match.getOpScore()) {
                result.setText(itemView.getResources().getString(R.string.win));
                result.setTextColor(itemView.getResources().getColor(R.color.win));
            } else if (match.getOurScore() < match.getOpScore()) {
                result.setText(itemView.getResources().getString(R.string.lose));
                result.setTextColor(itemView.getResources().getColor(R.color.lose));
            } else {
                result.setText(itemView.getResources().getString(R.string.draw));
                result.setTextColor(itemView.getResources().getColor(R.color.draw));
            }
        }
    }

    @NonNull
    @Override
    public MatchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.match_list_recycler, parent, false);

        return new MatchListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchListAdapter.ViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.setItem(match);
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }
}