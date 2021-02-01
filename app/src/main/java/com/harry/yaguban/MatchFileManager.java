package com.harry.yaguban;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MatchFileManager {
    private static final String matchRepository = "match_repository";

    public static void saveMatch(Context context, Match match) {
        Gson gson = new Gson();
        String json = gson.toJson(match);

        SharedPreferences pref = context.getSharedPreferences(matchRepository, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("match", json);
        editor.apply();
    }

    public static Match loadMatch(Context context) {
        Gson gson = new Gson();

        SharedPreferences pref = context.getSharedPreferences(matchRepository, Context.MODE_PRIVATE);
        String json = pref.getString("match", "");

        return gson.fromJson(json, Match.class);
    }
}
