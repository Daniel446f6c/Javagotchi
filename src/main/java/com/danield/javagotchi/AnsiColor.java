package com.danield.javagotchi;

public enum AnsiColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m");

    private final String colorCode;

    AnsiColor(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getValue() {
        return colorCode;
    }
}
