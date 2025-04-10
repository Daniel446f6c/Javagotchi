package com.danield.javagotchi.entities;

public class BigHealthPotion extends ConsumableItem {

    public BigHealthPotion() {
        super("Big Health Potion (+40 HP)", 20, 40);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setHealth(entity.getHealth() + bonusStat);
    }
}
