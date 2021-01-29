package com.harry.yaguban;

public class Player {
    public Player(String name, int backNumber, Position position) {
        this.name = name;
        this.backNumber = backNumber;
        this.position = position;
    }

    //Getter
    public String getName() { return name; }
    public int getBackNumber() { return backNumber; }
    public Position getPosition() { return position; }

    private String name;
    private int backNumber;
    private Position position;
}
