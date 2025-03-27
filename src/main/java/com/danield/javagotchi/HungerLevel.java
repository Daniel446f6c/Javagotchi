package com.danield.javagotchi;

public enum HungerLevel {
    MIN(0),
    LOW(5),
    MEDIUM(10),
    HIGH(18),
    MAX(20);

    private final int num;

    HungerLevel(int num) {
        this.num = num;
    }

    public int getValue() {
        return num;
    }
}