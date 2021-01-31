package com.harry.yaguban;

public class Player {
    public Player(String name, int backNumber, Position position) {
        this.name = name;
        this.backNumber = backNumber;
        this.position = position;
    }

    //Setter
    public void setName(String name) { this.name = name; }
    public void setBackNumber(int backNumber) { this.backNumber = backNumber; }
    public void setPosition(Position position) { this.position = position; }

    //Getter
    public String getName() { return name; }
    public int getBackNumber() { return backNumber; }
    public Position getPosition() { return position; }
    public String getSaveString() { return name + " " + backNumber + " " + position; }

    private String name;
    private int backNumber;
    private Position position;
}
