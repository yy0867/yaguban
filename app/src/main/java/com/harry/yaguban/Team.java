package com.harry.yaguban;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public ArrayList<Match> getMatchHistory() { return matchHistory; }
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public Player findPlayerByName(String name) {
        for (Player p : playerList) {
            if (name.equals(p.getName()))
                return p;
        }
        return null;
    }

    public ArrayList<String> getPlayerString() {
        ArrayList<String> list = new ArrayList<>();
        for (Player p : playerList) {
            list.add(p.getName());
        }
        return list;
    }

    public void setMatchHistory(ArrayList<Match> matchHistory) { this.matchHistory = matchHistory; }
    public void addMatchHistory(Match match) { matchHistory.add(match); }
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<Match> matchHistory = new ArrayList<>();
}
