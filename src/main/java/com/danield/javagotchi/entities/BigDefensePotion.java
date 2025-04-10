package com.danield.javagotchi.entities;

public class BigDefensePotion extends ConsumableItem {

    public BigDefensePotion() {
        super("Big Defense Potion (+10 Def)", 24, 10);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setDefense(entity.getDefense() + bonusStat);
    }
}
