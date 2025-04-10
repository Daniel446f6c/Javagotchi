package com.danield.javagotchi.entities;

public class SmallStrengthPotion extends ConsumableItem {

    public SmallStrengthPotion() {
        super("Small Strength Potion (+4 Str)", 14, 4);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setStrength(entity.getStrength() + bonusStat);
    }
}
