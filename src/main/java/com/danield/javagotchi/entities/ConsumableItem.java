package com.danield.javagotchi.entities;

import com.danield.javagotchi.utils.AnsiColor;

public abstract class ConsumableItem {

    protected String name;
    protected int cost;
    protected int bonusStat;

    public abstract void consume(PlayableEntity entity);

    public ConsumableItem(String name, int cost, int bonusStat) {
        this.name = name;
        this.cost = cost;
        this.bonusStat = bonusStat;
    }

    public String getName() {
        return "%s%s%s".formatted(AnsiColor.PURPLE.getCode(), name, AnsiColor.RESET.getCode());
    }

    public int getCost() {
        return cost;
    }

    public int getBonusStat() {
        return bonusStat;
    }

}
