package com.danield.javagotchi.entities;

import com.danield.javagotchi.utils.AnsiColor;

public class Windgotchi extends PlayableEntity {

    public Windgotchi(String name, boolean isNPC) {
        super(name, isNPC);
        element = NatureElement.WIND;
    }

    public Windgotchi(String name, int health, int strength, int defense, boolean isNPC) {
        super(name, health, strength, defense, isNPC);
        element = NatureElement.WIND;
    }

    @Override
    public String getColoredName() {
        return "%s%s%s".formatted(AnsiColor.YELLOW.getCode(), name, AnsiColor.RESET.getCode());
    }
}
