package com.danield.javagotchi.game;

import com.danield.javagotchi.entities.PlayableEntity;

public class CombatSystem {

    private CombatSystem() {}

    private static int oneIfNegativeOrZero(int num) {
        return Math.max(num, 1);
    }

    public static int evaluateDamage(PlayableEntity entity, PlayableEntity target) {

        if (entity.getElement().equals(target.getElement())) {
            return oneIfNegativeOrZero(entity.getStrength() - target.getDefense());
        }

        switch (entity.getElement()) {
            case FIRE -> {
                switch (target.getElement()) {
                    case WATER -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*0.9f - target.getDefense()*1.2f));
                    }
                    case EARTH -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength() - target.getDefense()*0.9f));
                    }
                    case WIND -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength() - target.getDefense()*1.2f));
                    }
                }
            }
            case WATER -> {
                switch (target.getElement()) {
                    case FIRE -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.2f - target.getDefense()*0.9f));
                    }
                    case EARTH -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.1f - target.getDefense()*0.9f));
                    }
                    case WIND -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.1f - target.getDefense()));
                    }
                }
            }
            case EARTH -> {
                switch (target.getElement()) {
                    case FIRE -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.3f - target.getDefense()));
                    }
                    case WATER -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.1f - target.getDefense()));
                    }
                    case WIND -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.2f - target.getDefense()*0.9f));
                    }
                }
            }
            case WIND -> {
                switch (target.getElement()) {
                    case FIRE -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*1.2f - target.getDefense()*0.9f));
                    }
                    case WATER -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength() - target.getDefense()*1.2f));
                    }
                    case EARTH -> {
                        return oneIfNegativeOrZero(Math.round(entity.getStrength()*0.9f - target.getDefense()*1.2f));
                    }
                }
            }
        }
        return 0;
    }
}
