package com.harry.yaguban;

import android.graphics.Path;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<String> getPositionList() {

        return Stream.of(Path.Direction.values())
                .map(Enum::name).collect(Collectors.toCollection(ArrayList::new));
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
