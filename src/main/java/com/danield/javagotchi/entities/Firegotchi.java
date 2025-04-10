package com.danield.javagotchi.entities;

import com.danield.javagotchi.utils.AnsiColor;

public class Firegotchi extends PlayableEntity {

    public Firegotchi(String name, boolean isNPC) {
        super(name, isNPC);
        element = NatureElement.FIRE;
    }

    public Firegotchi(String name, int health, int strength, int defense, boolean isNPC) {
        super(name, health, strength, defense, isNPC);
        element = NatureElement.FIRE;
    }

    @Override
    public String getColoredName() {
        return "%s%s%s".formatted(AnsiColor.RED.getCode(), name, AnsiColor.RESET.getCode());
    }
}
