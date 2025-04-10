package com.danield.javagotchi.entities;

public class BigEnergyPotion extends ConsumableItem {

    public BigEnergyPotion() {
        super("Big Energy Potion (+100 Energy)", 20, 100);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setEnergy(entity.getEnergy() + 100);
    }
}
