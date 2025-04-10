package com.danield.javagotchi.game;

public class ShopMenu implements Displayable {

    private final String MENU;

    public ShopMenu() {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append("********************************************************\n");
        menuBuilder.append("************************* Shop *************************\n");
        menuBuilder.append("********************************************************\n");
        for (ShopMenuItems item : ShopMenuItems.values()) {
            menuBuilder.append("****** (%s) %s | %s Coins\n".formatted(item.ordinal(), item.getItem().getName(), item.getItem().getCost()));
        }
        MENU = menuBuilder.toString();
    }

    @Override
    public void display() {
        System.out.println(MENU);
    }
}
