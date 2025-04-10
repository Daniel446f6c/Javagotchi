package com.danield.javagotchi.game;

import com.danield.javagotchi.entities.*;
import com.danield.javagotchi.utils.GameUtils;

public class PlayerMode extends AutoplayMode {

    private final ShopMenu SHOP_MENU = new ShopMenu();
    private final int numberOfPlayers;

    public PlayerMode(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    protected void initialize() {
        String playerName;
        NatureElement playerElement;
        if (numberOfPlayers == 1) {
            GameUtils.animateOutput("Welcome to Single Player Mode\n\n", 20, 200);

            playerName = chooseName("");
            playerElement = chooseElement("");
            entityList.add(generatePlayer(playerName, playerElement));
        }
        else {
            GameUtils.animateOutput("Welcome to Two Player Mode\n\n", 20, 200);

            playerName = chooseName("1");
            playerElement = chooseElement("1");
            entityList.add(generatePlayer(playerName, playerElement));

            playerName = chooseName("2");
            playerElement = chooseElement("2");
            entityList.add(generatePlayer(playerName, playerElement));
        }

        generateEntities(chooseNumberOfEntities());

        GameUtils.clearConsole();
    }

    @Override
    protected void gameLoop() {
        while (entityList.size() > 1) {
            // wrong recommendation
            // cant use for-each when removing from the list its iterating over (throws "ConcurrentModificationException")
            for (int i = 0; i < entityList.size(); i++) {
                PlayableEntity entity = entityList.get(i);
                PlayableEntity target;
                displayEntities();
                if (entity.isNPC()) {
                    target = selectRandomTarget(entity);
                    entity.attack(target);
                    collectCoinReward(entity);
                    lootAndRemoveIfKilled(entity, target);
                }
                else {
                    target = playerSelectTarget(entity);
                    entity.attack(target);
                    collectCoinReward(entity);
                    lootAndRemoveIfKilled(entity, target);
                    GameUtils.clearConsole();

                    playerLookUpShopAndBuy(entity);
                    GameUtils.clearConsole();

                    playerCheckInventoryIfNotEmpty(entity);
                }
                endOfRound(entity);
            }
        }
        displayTheWinner();
    }

    protected PlayableEntity playerSelectTarget(PlayableEntity player) {
        while (true) {
            GameUtils.animateOutput("%s - Who do you want to attack?: ".formatted(player.getColoredName()), 20);
            String targetName = GameUtils.getUserInputString();
            for (PlayableEntity entity : entityList) {
                if (targetName.equalsIgnoreCase(player.getName())) {
                    GameUtils.animateOutput("You can't attack yourself!\n", 20);
                    break;
                }
                if (entity.getName().equalsIgnoreCase(targetName)) {
                    return entity;
                }
            }
        }
    }

    protected void playerLookUpShopAndBuy(PlayableEntity player) {
        SHOP_MENU.display();
        System.out.println(player.toString());
        GameUtils.animateOutput("\nWant to buy something? If not type X\n", 20);
        int itemId;
        while (true) {
            String item = GameUtils.getUserInputString("Select: ");
            if (item.equalsIgnoreCase("X")) { return; }
            try {
                itemId = Integer.parseInt(item);
                if (itemId > -1 && itemId < ShopMenuItems.values().length) {
                    break;
                }
            } catch (NumberFormatException ignored) {}
        }
        for (ShopMenuItems item : ShopMenuItems.values()) {
            if (itemId == item.ordinal()) {
                if (player.getCoins() >= item.getItem().getCost()) {
                    GameUtils.animateOutput("\n%s bought %s".formatted(player.getColoredName(), item.getItem().getName()), 20, 400);
                    player.addToInventory(item.getItem());
                    player.setCoins(player.getCoins() - item.getItem().getCost());
                }
                else {
                    GameUtils.animateOutput("You don't have enough coins to buy %s\n".formatted(item.getItem().getName()), 20, 400);
                }
            }
        }
    }

    protected void playerCheckInventoryIfNotEmpty(PlayableEntity player) {
        if (!player.getInventory().isEmpty()) {
            System.out.println("********************************************************");
            System.out.println("********************** Inventory ***********************");
            System.out.println("********************************************************");
            for (int i = 0; i < player.getInventory().size(); i++) {
                System.out.printf("****** (%s) %s\n".formatted(i, player.getInventory().get(i).getName()));
            }
            System.out.println();
            GameUtils.animateOutput("\nWant to consume something? If not type X\n", 20);
            int itemId;
            while (true) {
                String item = GameUtils.getUserInputString("Select: ");
                if (item.equalsIgnoreCase("X")) { return; }
                try {
                    itemId = Integer.parseInt(item);
                    if (itemId > -1 && itemId < player.getInventory().size()) {
                        break;
                    }
                } catch (NumberFormatException ignored) {}
            }
            player.getInventory().get(itemId).consume(player);
            GameUtils.animateOutput("\n%s used %s".formatted(player.getColoredName(), player.getInventory().get(itemId).getName()), 20, 400);
            player.getInventory().remove(itemId);
        }
    }

    protected PlayableEntity generatePlayer(String name, NatureElement element) {
        switch (element) {
            case FIRE -> { return new Firegotchi(name, false); }
            case WATER -> { return new Watergotchi(name, false); }
            case EARTH -> { return new Earthgotchi(name, false); }
            case WIND -> { return new Windgotchi(name, false); }
            default -> { return null; }
        }
    }

    protected int chooseNumberOfEntities() {
        GameUtils.animateOutput("\nChoose how many enemies to create(1-9):\n", 20);
        String numberOfEntities;
        do {
            numberOfEntities = GameUtils.getUserInputString("Select: ");
        } while (!numberOfEntities.matches("^[1-9]"));
        return Integer.parseInt(numberOfEntities);
    }

    protected String chooseName(String playerNumber) {
        String name;
        do {
            GameUtils.animateOutput("\nPlayer %s - Choose your name: ".formatted(playerNumber), 20);
            name = GameUtils.getUserInputString();
        } while (name.isEmpty());
        return name;
    }

    protected NatureElement chooseElement(String playerNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NatureElement element : NatureElement.values()) {
            stringBuilder.append(" (%s) ".formatted(element.ordinal()));
            stringBuilder.append(element.name());
        }
        GameUtils.animateOutput("\nPlayer %s - Choose your element: %s\n".formatted(playerNumber, stringBuilder.toString()), 20);
        String selection;
        do {
            selection = GameUtils.getUserInputString("Select: ");
        } while (!selection.matches("^[0-3]"));
        return NatureElement.values()[Integer.parseInt(selection)];
    }
}
