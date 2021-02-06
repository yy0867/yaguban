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
    public void increaseTimesAtBat() { timesAtBat++; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void increaseHit1() { hit1++; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void increaseHit2() { hit2++; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void increaseHit3() { hit3++; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void increaseHomerun() { homerun++; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void increaseStrikeOut() { strikeOut++; }
    public void increaseBall4() { ball4++; calcBasePercent(); }
    public void increaseSacrificeFly() { sacrificeFly++; calcBasePercent(); }
    public void decreaseTimesAtBat() {
        if (timesAtBat != 0) timesAtBat--;
        calcBattingAvg();
        calcBasePercent();
        calcSluggingAvg();
    }
    public void decreaseHit1() { if (hit1 != 0) hit1--; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void decreaseHit2() { if (hit2 != 0) hit2--; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void decreaseHit3() { if (hit3 != 0) hit3--; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void decreaseHomerun() { if (homerun != 0) homerun--; calcBattingAvg(); calcBasePercent(); calcSluggingAvg(); }
    public void decreaseStrikeOut() { if (strikeOut != 0) strikeOut--; }
    public void decreaseBall4() { if (ball4 != 0) ball4--; calcBasePercent(); }
    public void decreaseSacrificeFly() { if (sacrificeFly != 0) sacrificeFly--; calcBasePercent(); }
    public void calcBattingAvg() { battingAvg = (getTimesAtBat() != 0) ? getHit() / (double) (getTimesAtBat()) : 0; }
    public void calcBasePercent() { basePercent = (getTimesAtBat() + getBall4() + getSacrificeFly() != 0) ?
            (getHit() + getBall4()) / (double) (getTimesAtBat() + getBall4() + getSacrificeFly()) : 0; }
    public void calcSluggingAvg() { sluggingAvg = (getTimesAtBat() != 0) ? (hit1 + 2 * hit2 + 3 * hit3 * 4 * homerun) / (double) getTimesAtBat() : 0; }

    public void increaseStrikeCount() { strikeCount++; }
    public void increaseBall4Count() { ball4Count++; }
    public void increaseHittedCount() { hittedCount++; }
    public void increasePitchCount() { pitchCount++; }
    public void increaseLosePoint() { losePoint++; calcERA(); }
    public void increaseInnings() {
        innings += 0.1;
        if (innings - (int) innings > 0.29) innings += 0.7;
        calcERA();
    }
    public void calcERA() {
        //era = (innings == 0) ? 0 : losePoint*9 / innings;
        double calcInnings = 1;
        if (innings == 0) {
            era = 0;
            return;
        } else if (Math.round(innings * 10) % 10 == 1) {
            calcInnings = (int) innings + (1 / 3.0);
        } else if (Math.round(innings * 10) % 10 == 2) {
            calcInnings = (int) innings + (2 / 3.0);
        }
        era = losePoint * 9 / calcInnings;
    }

    public void decreaseStrikeCount() { if (strikeCount != 0) strikeCount--; }
    public void decreaseBall4Count() { if (ball4Count != 0) ball4Count--; }
    public void decreaseHittedCount() { if (hittedCount != 0) hittedCount--; }
    public void decreasePitchCount() { if (pitchCount != 0) pitchCount--; }
    public void decreaseLosePoint() { if (losePoint != 0) losePoint--; calcERA(); }
    public void decreaseInnings() {
        if (Math.round(innings * 10) == 0) {
            innings = 0;
            return;
        }

        if (Math.round(innings * 10) % 10 == 0) innings -= 0.8;
        else innings -= 0.1;

        if (Math.round(innings * 10) == 0) {
            innings = 0;
        }

        calcERA();
    }

    //Getter
    public String getName() { return name; }
    public int getBackNumber() { return backNumber; }
    public Position getPosition() { return position; }
    public int getTimesAtBat() { return timesAtBat; }
    public int getHit() { return hit1 + hit2 + hit3 + homerun; }
    public int getHit1() { return hit1; }
    public int getHit2() { return hit2; }
    public int getHit3() { return hit3; }
    public int getHomerun() { return homerun; }
    public int getStrikeOut() { return strikeOut; }
    public int getBall4() { return ball4; }
    public int getSacrificeFly() { return sacrificeFly; }
    public double getBattingAvg() { return battingAvg; }
    public double getBasePercent() { return basePercent; }
    public double getSluggingAvg() { return sluggingAvg; }

    public int getStrikeCount() { return strikeCount; }
    public int getBall4Count() { return ball4Count; }
    public int getHittedCount() { return hittedCount; }
    public int getPitchCount() { return pitchCount; }
    public int getLosePoint() { return losePoint; }
    public double getEra() { return era; }
    public double getInnings() { return innings; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    private boolean isSelected;

    private String name;
    private int backNumber;
    private Position position;

    private int timesAtBat, strikeOut, ball4, sacrificeFly;
    private int hit1, hit2, hit3, homerun;
    private double battingAvg, basePercent, sluggingAvg;

    private int strikeCount, ball4Count, hittedCount, pitchCount, losePoint;
    private double era, innings;
}
