package com.danield.javagotchi.game;

public class StartMenu implements Displayable {

    private final String MENU;

    public StartMenu() {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append("********************************************************\n");
        menuBuilder.append("********************** Start Menu **********************\n");
        menuBuilder.append("********************************************************\n");
        for (StartMenuItems item : StartMenuItems.values()) {
            menuBuilder.append("****** (%s) %s\n".formatted(item.ordinal(), item.name()));
        }
        MENU = menuBuilder.toString();
    }

    @Override
    public void display() {
        System.out.println(MENU);
    }

}
