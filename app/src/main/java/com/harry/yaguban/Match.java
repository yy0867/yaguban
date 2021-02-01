package com.harry.yaguban;

import android.content.Context;

public class Match {
    public Match(Context context) {
        ourScore = opScore = 0;
        opName = "";
        matchOngoing = false;
    }

    public void increaseOurScore() { ourScore++; }
    public void increaseOpScore() { opScore++; }
    public void decreaseOurScore() {
        if (ourScore == 0) return;
        ourScore--;
    }

    public void decreaseOpScore() {
        if (opScore == 0) return;
        opScore--;
    }

    public void setOpName(String opName) { this.opName = opName; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(String date) { this.date = date; }
    public void startMatch() { matchOngoing = true; }
    public void endMatch() { matchOngoing = false; }

    public String getOpName() { return opName; }
    public String getLocation() { return location; }
    public int getOurScore() { return ourScore; }
    public int getOpScore() { return opScore; }

    boolean matchOngoing;
    int ourScore, opScore;
    String location;
    String opName;
    String date;
}
