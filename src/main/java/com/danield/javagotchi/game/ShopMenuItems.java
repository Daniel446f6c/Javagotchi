package com.danield.javagotchi.game;

import com.danield.javagotchi.entities.ConsumableItem;
import com.danield.javagotchi.entities.SmallHealthPotion;
import com.danield.javagotchi.entities.SmallStrengthPotion;
import com.danield.javagotchi.entities.SmallDefensePotion;
import com.danield.javagotchi.entities.SmallEnergyPotion;
import com.danield.javagotchi.entities.BigHealthPotion;
import com.danield.javagotchi.entities.BigStrengthPotion;
import com.danield.javagotchi.entities.BigDefensePotion;
import com.danield.javagotchi.entities.BigEnergyPotion;


public enum ShopMenuItems {
    SMALL_HEALTH_POTION(new SmallHealthPotion()),
    SMALL_STRENGTH_POTION(new SmallStrengthPotion()),
    SMALL_DEFENSE_POTION(new SmallDefensePotion()),
    SMALL_ENERGY_POTION(new SmallEnergyPotion()),
    BIG_HEALTH_POTION(new BigHealthPotion()),
    BIG_STRENGTH_POTION(new BigStrengthPotion()),
    BIG_DEFENSE_POTION(new BigDefensePotion()),
    BIG_ENERGY_POTION(new BigEnergyPotion());

    private final ConsumableItem item;

    ShopMenuItems(ConsumableItem item) {
        this.item = item;
    }

    public ConsumableItem getItem() {
        return item;
    }

}
