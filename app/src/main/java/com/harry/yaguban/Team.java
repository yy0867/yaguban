package com.harry.yaguban;

import java.util.ArrayList;

public class Team {
    public ArrayList<Match> getMatchHistory() { return matchHistory; }
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void addMatchHistory(Match match) { matchHistory.add(match); }
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<Match> matchHistory = new ArrayList<>();
}
