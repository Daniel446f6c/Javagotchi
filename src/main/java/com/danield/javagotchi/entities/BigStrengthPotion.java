package com.danield.javagotchi.entities;

public class BigStrengthPotion extends ConsumableItem {

    public BigStrengthPotion() {
        super("Big Strength Potion (+12 Str)", 28, 12);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setStrength(entity.getStrength() + bonusStat);
    }
}
