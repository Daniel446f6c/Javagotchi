package com.danield.javagotchi.entities;

public class SmallHealthPotion extends ConsumableItem {

    public SmallHealthPotion() {
        super("Small Health Potion (+15 HP)", 10, 15);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setHealth(entity.getHealth() + bonusStat);
    }
}
