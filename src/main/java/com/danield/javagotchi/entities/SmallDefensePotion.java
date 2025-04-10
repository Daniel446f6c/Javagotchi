package com.danield.javagotchi.entities;

public class SmallDefensePotion extends ConsumableItem {

    public SmallDefensePotion() {
        super("Small Defense Potion (+4 Def)", 12, 4);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setDefense(entity.getDefense() + bonusStat);
    }
}
