package com.danield.javagotchi;

public enum AnsiColor {
    RESET("\u001B[0m", "RESET"),
    RED("\u001B[31m", "RED"),
    GREEN("\u001B[32m", "GREEN"),
    YELLOW("\u001B[33m", "YELLOW"),
    BLUE("\u001B[34m", "BLUE"),
    PURPLE("\u001B[35m", "PURPLE"),
    CYAN("\u001B[36m", "CYAN");

    private final String colorCode;
    private final String colorName;

    AnsiColor(String colorCode, String colorName) {
        this.colorCode = colorCode;
        this.colorName = colorName;
    }

    public String getCode() {
        return colorCode;
    }

    public String getName() {
        return colorName;
    }
}
