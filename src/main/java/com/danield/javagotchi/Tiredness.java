package com.danield.javagotchi;

public enum Tiredness {
    MIN(0),
    LOW(5),
    MEDIUM(10),
    HIGH(18),
    MAX(20);

    private final int num;

    Tiredness(int num) {
        this.num = num;
    }

    public int getValue() {
        return num;
    }
}