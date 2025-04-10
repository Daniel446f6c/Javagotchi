package com.danield.javagotchi.entities;

import com.danield.javagotchi.utils.AnsiColor;

public class Earthgotchi extends PlayableEntity {

    public Earthgotchi(String name, boolean isNPC) {
        super(name, isNPC);
        element = NatureElement.EARTH;
    }

    public Earthgotchi(String name, int health, int strength, int defense, boolean isNPC) {
        super(name, health, strength, defense, isNPC);
        element = NatureElement.EARTH;
    }

    @Override
    public String getColoredName() {
        return "%s%s%s".formatted(AnsiColor.GREEN.getCode(), name, AnsiColor.RESET.getCode());
    }
}
