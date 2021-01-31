package com.harry.yaguban;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class TeamFileManager {
    private static final String teamRepository = "team_repository";

    public static void saveTeam(Context context, Team team) {
        Gson gson = new Gson();
        String json = gson.toJson(team);

        SharedPreferences pref = context.getSharedPreferences(teamRepository, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("team", json);
        editor.apply();
    }

    public static Team loadTeam(Context context) {
        Gson gson = new Gson();

        SharedPreferences pref = context.getSharedPreferences(teamRepository, Context.MODE_PRIVATE);
        String json = pref.getString("team", "");

        Team team = gson.fromJson(json, Team.class);

        return team == null ? new Team() : team;
    }
}
