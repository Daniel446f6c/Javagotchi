package com.danield.javagotchi.game;

import com.danield.javagotchi.entities.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class GameMode {

    private final int ENTITY_HP = 150;
    private final int ENTITY_STR = RANDOM.nextInt(30, 41);
    private final int ENTITY_DEF = RANDOM.nextInt(4, 11);

    protected static final Random RANDOM = new Random();
    protected ArrayList<PlayableEntity> entityList = new ArrayList<>();

    protected abstract void initialize();
    protected abstract void gameLoop();

    protected void generateEntities() {
        for (int i = 0; i < RANDOM.nextInt(2, NatureElement.FIRE.getNames().length+1); i++) {
            entityList.add(new Firegotchi(NatureElement.FIRE.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
        }
        for (int i = 0; i < RANDOM.nextInt(2, NatureElement.WATER.getNames().length+1); i++) {
            entityList.add(new Watergotchi(NatureElement.WATER.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
        }
        for (int i = 0; i < RANDOM.nextInt(2, NatureElement.EARTH.getNames().length+1); i++) {
            entityList.add(new Earthgotchi(NatureElement.EARTH.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
        }
        for (int i = 0; i < RANDOM.nextInt(2, NatureElement.WIND.getNames().length+1); i++) {
            entityList.add(new Windgotchi(NatureElement.WIND.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
        }
    }

    protected void generateEntities(int numberOfEntities) {
        for (int i = 0; i < numberOfEntities; i++) {
            switch (RANDOM.nextInt(4)) {
                case 0 -> entityList.add(new Firegotchi(NatureElement.FIRE.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
                case 1 -> entityList.add(new Watergotchi(NatureElement.WATER.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
                case 2 -> entityList.add(new Earthgotchi(NatureElement.EARTH.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
                case 3 -> entityList.add(new Windgotchi(NatureElement.WIND.getNames()[i], ENTITY_HP, ENTITY_STR, ENTITY_DEF, true));
            }
        }
    }

    public void startGame() {
        initialize();
        gameLoop();
    }

}
