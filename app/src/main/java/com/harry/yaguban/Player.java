package com.harry.yaguban;

import android.os.Parcelable;

import java.io.Serializable;

public class Player implements Serializable {
    public Player(String name, int backNumber, Position position) {
        this.name = name;
        this.backNumber = backNumber;
        this.position = position;
        timesAtBat = strikeOut = ball4 = sacrificeFly = 0;
        battingAvg = basePercent = sluggingAvg = 0;
        hit1 = hit2 = hit3 = homerun = 0;
    }

    public Player(Player player) {
        setName(player.getName());
        setBackNumber(player.getBackNumber());
        setPosition(player.getPosition());
        setSelected(player.isSelected());
        timesAtBat = strikeOut = ball4 = sacrificeFly = 0;
        battingAvg = basePercent = sluggingAvg = 0;
        hit1 = hit2 = hit3 = homerun = 0;
    }

    //Setter
    public void setName(String name) { this.name = name; }
    public void setBackNumber(int backNumber) { this.backNumber = backNumber; }
    public void setPosition(Position position) { this.position = position; }
    public void increaseTimesAtBat() { timesAtBat++; }
    public void increaseHit1() { hit1++; calcBattingAvg(); calcBasePercent(); }
    public void increaseHit2() { hit2++; calcBattingAvg(); calcBasePercent(); }
    public void increaseHit3() { hit3++; calcBattingAvg(); calcBasePercent(); }
    public void increaseHomerun() { homerun++; calcBattingAvg(); calcBasePercent(); }
    public void increaseStrikeOut() { strikeOut++; }
    public void increaseBall4() { ball4++; calcBasePercent(); }
    public void increaseSacrificeFly() { sacrificeFly++; calcBasePercent(); }
    public void decreaseTimesAtBat() {
        if (timesAtBat != 0) timesAtBat--;
        calcBattingAvg();
        calcBasePercent();
        calcSluggingAvg();
    }
    public void decreaseHit1() { if (hit1 != 0) hit1--; }
    public void decreaseHit2() { if (hit2 != 0) hit2--; }
    public void decreaseHit3() { if (hit3 != 0) hit3--; }
    public void decreaseHomerun() { if (homerun != 0) homerun--; }
    public void decreaseStrikeOut() { if (strikeOut != 0) strikeOut--; }
    public void decreaseBall4() { if (ball4 != 0) ball4--; }
    public void decreaseSacrificeFly() { if (sacrificeFly != 0) sacrificeFly--; }
    public void calcBattingAvg() { battingAvg = (getTimesAtBat() != 0) ? getHit() / (double) (getTimesAtBat()) : 0; }
    public void calcBasePercent() { basePercent = (getTimesAtBat() + getBall4() + getSacrificeFly() != 0) ?
            (getHit() + getBall4()) / (double) (getTimesAtBat() + getBall4() + getSacrificeFly()) : 0; }
    public void calcSluggingAvg() { sluggingAvg = (getTimesAtBat() != 0) ? (hit1 + 2 * hit2 + 3 * hit3 * 4 * homerun) / (double) getTimesAtBat() : 0; }

    //Getter
    public String getName() { return name; }
    public int getBackNumber() { return backNumber; }
    public Position getPosition() { return position; }
    public int getTimesAtBat() { return timesAtBat; }
    public int getHit() { return hit1 + hit2 + hit3 + homerun; }
    public int getStrikeOut() { return strikeOut; }
    public int getBall4() { return ball4; }
    public int getSacrificeFly() { return sacrificeFly; }
    public double getBattingAvg() { return battingAvg; }
    public double getBasePercent() { return basePercent; }
    public double getSluggingAvg() { return sluggingAvg; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    private boolean isSelected;

    private String name;
    private int backNumber;
    private Position position;

    private int timesAtBat, strikeOut, ball4, sacrificeFly;
    private int hit1, hit2, hit3, homerun;
    private double battingAvg, basePercent, sluggingAvg;
}
