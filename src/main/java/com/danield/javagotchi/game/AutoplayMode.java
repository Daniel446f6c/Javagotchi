package com.danield.javagotchi.game;

import com.danield.javagotchi.entities.*;
import com.danield.javagotchi.utils.GameUtils;

public class AutoplayMode extends GameMode {

    @Override
    protected void initialize() {
        GameUtils.animateOutput("Welcome to Autoplay Mode\n", 20, 200);
        GameUtils.animateOutput("\nGenerating Entities...", 20, 400);
        generateEntities();
        GameUtils.clearConsole();
    }

    @Override
    protected void gameLoop() {
        while (entityList.size() > 1) {
            // wrong recommendation
            // cant use for-each when removing from the list its iterating over (throws "ConcurrentModificationException")
            for (int i = 0; i < entityList.size(); i++) {
                PlayableEntity entity = entityList.get(i);
                displayEntities();
                PlayableEntity target = selectRandomTarget(entity);
                entity.attack(target);
                collectCoinReward(entity);
                lootAndRemoveIfKilled(entity, target);
                endOfRound(entity);
            }
        }
        displayTheWinner();
    }

    protected void displayTheWinner() {
        GameUtils.animateOutput("%s won the game!\n".formatted(entityList.getFirst().getColoredName()), 20, 200);
        GameUtils.animateOutput(entityList.getLast().toString() + "\n", 20, 400);
        GameUtils.getUserInputString("Press any key to continue...");
        GameUtils.clearConsole();
    }

    protected void lootAndRemoveIfKilled(PlayableEntity entity, PlayableEntity target) {
        if (!target.isAlive()) {
            target.died(entity);
            entity.loot(target);
            entityList.remove(target);
        }
    }

    protected PlayableEntity selectRandomTarget(PlayableEntity entity) {
        PlayableEntity target;
        do {
            target = entityList.get(RANDOM.nextInt(entityList.size()));
        } while (target.equals(entity));
        return target;
    }

    protected void displayEntities() {
        for (PlayableEntity entity2 : entityList) {
            System.out.println(entity2.toString() + "\n");
        }
        System.out.println("-----------------------------------------------------------------------------------------------\n");
    }

    protected void collectCoinReward(PlayableEntity entity) {
        int rewardCoins = RANDOM.nextInt(3, 9);
        entity.setCoins(entity.getCoins() + rewardCoins);
        GameUtils.animateOutput("\nThis attack rewards %s with %s coins!\n".formatted(entity.getColoredName(), rewardCoins), 20, 200);
    }

    protected void endOfRound(PlayableEntity entity) {
        entity.setLevel(entity.getLevel() + 1);
        if (entity.isNPC()) {
            lookUpShopAndBuy(entity);
            consumeRandomItemFromInventory(entity);
        }
        GameUtils.clearConsole();
    }

    private void lookUpShopAndBuy(PlayableEntity entity) {
        for (ShopMenuItems item : ShopMenuItems.values()) {
            if (entity.getCoins() >= item.getItem().getCost()) {
                if (RANDOM.nextBoolean()) {
                    return;
                }
                GameUtils.animateOutput("\n%s bought %s".formatted(entity.getColoredName(), item.getItem().getName()), 20, 400);
                entity.addToInventory(item.getItem());
                entity.setCoins(entity.getCoins() - item.getItem().getCost());
            }
        }
    }

    private void consumeRandomItemFromInventory(PlayableEntity entity) {
        int inventorySize = entity.getInventory().size();
        if (inventorySize == 0) { return; }
        if (inventorySize == 1) {
            entity.getInventory().getFirst().consume(entity);
            GameUtils.animateOutput("\n%s used %s".formatted(entity.getColoredName(), entity.getInventory().getFirst().getName()), 20, 400);
            entity.getInventory().removeFirst();
            return;
        }
        int randomItem = RANDOM.nextInt(inventorySize);
        entity.getInventory().get(randomItem).consume(entity);
        GameUtils.animateOutput("\n%s used %s".formatted(entity.getColoredName(), entity.getInventory().get(randomItem).getName()), 20, 400);
        entity.getInventory().remove(randomItem);
    }
}
