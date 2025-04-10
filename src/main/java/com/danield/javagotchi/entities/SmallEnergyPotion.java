package com.danield.javagotchi.entities;

public class SmallEnergyPotion extends ConsumableItem {

    public SmallEnergyPotion() {
        super("Small Energy Potion (+45 Energy)", 10, 45);
    }

    @Override
    public void consume(PlayableEntity entity) {
        entity.setEnergy(entity.getEnergy() + 45);
    }
}
