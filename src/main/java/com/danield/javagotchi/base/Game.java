package com.danield.javagotchi.base;

import com.danield.javagotchi.game.*;
import com.danield.javagotchi.utils.GameUtils;

public class Game {

    private Game() {}
    private static final SplashScreen SPLASH_SCREEN = new SplashScreen();
    private static final StartMenu START_MENU = new StartMenu();

    private static void gameLoop() {
        SPLASH_SCREEN.display();
        GameMode gameMode;
        while (true) {
            START_MENU.display();
            String option = GameUtils.getUserInputString("Select: ");

            if (option.equals(String.valueOf(StartMenuItems.AUTOPLAY.ordinal()))) {
                GameUtils.clearConsole();
                gameMode = new AutoplayMode();
                gameMode.startGame();
            }
            else if (option.equals(String.valueOf(StartMenuItems.SINGLEPLAYER.ordinal()))) {
                GameUtils.clearConsole();
                gameMode = new PlayerMode(1);
                gameMode.startGame();
            }
            else if (option.equals(String.valueOf(StartMenuItems.TWOPLAYERS.ordinal()))) {
                GameUtils.clearConsole();
                gameMode = new PlayerMode(2);
                gameMode.startGame();
            }
            else if (option.equals(String.valueOf(StartMenuItems.EXIT.ordinal()))) {
                GameUtils.clearConsole();
                GameUtils.animateOutput("Thanks for playing!", 20, 500);
                break;
            }
            else {
                GameUtils.animateOutput("Invalid selection!", 20, 200);
                GameUtils.clearConsole();
            }
        }
    }

    public static void start() {
        gameLoop();
    }

}
