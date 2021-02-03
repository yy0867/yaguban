package com.harry.yaguban;

import android.graphics.Path;
import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Position implements Serializable {
    PITCHER(1, "투수"),
    CATCHER(2, "포수"),
    FIRST(3, "1루수"),
    SECOND(4, "2루수"),
    THIRD(5, "3루수"),
    SHORTSTOP(6, "유격수"),
    LEFT(7, "좌익수"),
    CENTER(8, "중견수"),
    RIGHT(9, "우익수"),
    DH(10, "지명타자");

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    private final int order;
    private final String name;

    private Position(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public static ArrayList<String> getPositionListToString() {
        Position[] position = Position.values();
        ArrayList<String> names = new ArrayList<>();

        for (Position p : position) {
            names.add(p.name);
        }

        return names;
    }

    public static Position getPositionByNumber(int order) {
        Position[] position = Position.values();

        for (Position p : position) {
            if (p.order - 1 == order) {
                return p;
            }
        }
        return null;
    }

    public static Position getPositionByName(String name) {
        Position[] position = Position.values();

        for (Position p : position) {
            if (p.name.equals(name)) {
                return p;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
