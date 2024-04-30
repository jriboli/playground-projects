package com.populateDB.enums;

public enum Difficulty {
    TRIVIAL(1),
    EASY(2),
    MEDIUM(3),
    CHALLENGING(4),
    HARD(5),
    EXTREME(6),
    SUICIDE(7),
    IMPOSSIBLE(8),
    HELLDIVE(9);

    public final int value;

    private Difficulty(int value) {
        this.value = value;
    }
}
