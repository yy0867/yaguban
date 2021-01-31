package com.harry.yaguban;

import java.util.ArrayList;

public class Team {

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    ArrayList<Player> playerList = new ArrayList<>();
}
