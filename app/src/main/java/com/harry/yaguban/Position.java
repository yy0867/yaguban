package com.harry.yaguban;

public enum Position {
    CATCHER("포수"),
    PITCHER("투수"),
    FIRST("1루수"),
    SECOND("2루수"),
    THIRD("3루수"),
    SHORTSTOP("유격수"),
    LEFT("좌익수"),
    CENTER("중견수"),
    RIGHT("우익수"),
    DH("지명타자");

    private final String name;

    private Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
