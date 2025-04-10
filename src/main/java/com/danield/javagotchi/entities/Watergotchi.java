package com.danield.javagotchi.entities;

import com.danield.javagotchi.utils.AnsiColor;

public class Watergotchi extends PlayableEntity {

    public Watergotchi(String name, boolean isNPC) {
        super(name, isNPC);
        element = NatureElement.WATER;
    }

    public Watergotchi(String name, int health, int strength, int defense, boolean isNPC) {
        super(name, health, strength, defense, isNPC);
        element = NatureElement.WATER;
    }

    @Override
    public String getColoredName() {
        return "%s%s%s".formatted(AnsiColor.CYAN.getCode(), name, AnsiColor.RESET.getCode());
    }
}
