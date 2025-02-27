package com.danield.javagotchi;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    private Game() {}
    private static final Scanner USER_INPUT = new Scanner(System.in);
    private static final Javagotchi JAVAGOTCHI = new Javagotchi();
    private static final String SPLASH_IMAGE = """
            
            
            
            
            
            \s\s\s\s\s\s  ________     ____     __    __     ____        _____      ____     ________     ____   __    __    _____\s
            \s\s\s\s\s\s (___  ___)   (    )    ) )  ( (    (    )      / ___ \\    / __ \\   (___  ___)   / ___) (  \\  /  )  (_   _)
            \s\s\s\s\s\s     ) )      / /\\ \\   ( (    ) )   / /\\ \\     / /   \\_)  / /  \\ \\      ) )     / /      \\ (__) /     | | \s
            \s\s\s\s\s\s    ( (      ( (__) )   \\ \\  / /   ( (__) )   ( (  ____  ( ()  () )    ( (     ( (        ) __ (      | | \s
            \s\s\s\s\s\s __  ) )      )    (     \\ \\/ /     )    (    ( ( (__  ) ( ()  () )     ) )    ( (       ( (  ) )     | | \s
            \s\s\s\s\s\s( (_/ /      /  /\\  \\     \\  /     /  /\\  \\    \\ \\__/ /   \\ \\__/ /     ( (      \\ \\___    ) )( (     _| |_
            \s\s\s\s\s\s \\___/      /__(  )__\\     \\/     /__(  )__\\    \\____/     \\____/      /__\\      \\____)  /_/  \\_\\   /_____\\
            
            
            
            
                                 ▄▀░░▌                                                                    ▄▀▀▀▀█
                               ▄▀▐░░░▌                                                                   █▀░░░░█
                            ▄▀▀▒▐▒░░░▌                                                                  █░░░░░░█▀▀▀▄▄      ▓
                 ▄▀▀▄   ▄▄▀▀▒▒▒▒▌▒▒░░▌                                                                 ▄█░░░░░░░░░░░░▀▀▄▄ ▓▓▓
                ▐▒░░░▀▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒█                                                                ▄█░░░░░░░░░░░░░░░░░▀▓▓▓▓▀▀▀█
                ▌▒░░░░▒▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄                                                           ▀▄▄█░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓░░█
                ▐▒░░░░░▒▒▒▒▒▒▒▒▒▌▒▐▒▒▒▒▒▀▄                                                         ▀▄█░▀▄░░░░▄▄░░░░░░▓▓▓▓▓▓▓░░▓▓▓▓▓▓
                ▌▀▄░░▒▒▒▒▒▒▒▒▐▒▒▒▌▒▌▒▄▄▒▒▐                                                         ▄▀█▀▄░░░░███░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓
               ▌▌▒▒▀▒▒▒▒▒▒▒▒▒▒▐▒▒▒▒▒█▄█▌▒▒▌                                                         ▀█▀▄░░░░▀▀░░░░░░░░░▄▄░░░▓▓▓▓▓
             ▄▀▒▐▒▒▒▒▒▒▒▒▒▒▒▄▀█▌▒▒▒▒▒▀▀▒▒▐░░░▄                                                       ▀█░░░░░░░░▄▄░░░░░███░░░░▓▓░█
            ▀▒▒▒▒▌▒▒▒▒▒▒▒▄▒▐███▌▄▒▒▒▒▒▒▒▄▀▀▀▀                                                         ▀█░░░░░░█░░▀▄░░░▀▀░░░░░▓░█▀
            ▒▒▒▒▒▐▒▒▒▒▒▄▀▒▒▒▀▀▀▒▒▒▒▄█▀░░▒▌▀▀▄▄                                                         ▀█░░░░░░▀▄▄▀░░░░░░░░▀▄░█▀
            ▒▒▒▒▒▒█▒▄▄▀▒▒▒▒▒▒▒▒▒▒▒░░▐▒▀▄▀▄░░░░▀                                                          ▀█▄░░░░░░░░░░░░░▀▄░░██
            ▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▄▒▒▒▒▄▀▒▒▒▌░░▀▄                                                                ▀█▄░░░░░░░░▀▄░░██▀░▀
            ▒▒▒▒▒▒▒▒▀▄▒▒▒▒▒▒▒▒▀▀▀▀▒▒▒▄▀                                                                        ▀▀▄▄▄▄▄▄▄█▀█░░▀▄
                                                                                                                           ▀
            """;
    private static String option;
    private static boolean run = true;
    private static int rounds = 0;
    private static boolean isFirstRound = true;

    private static void splashScreen() {
        for (int i = 0; i < 6; i++) {
            for (AnsiColor color : AnsiColor.values()) {
                if (color.equals(AnsiColor.RESET)) continue;
                clearConsole();
                System.out.print(color.getCode() + SPLASH_IMAGE);
                try {
                    Thread.sleep(120);
                } catch (InterruptedException ignored) {}
            }
        }

        System.out.print(AnsiColor.RESET.getCode());
        clearConsole();
    }

    private static void getUserInput() {
        System.out.print("What are we doing next?: ");
        option = USER_INPUT.nextLine().strip();
        System.out.println("\n");
    }

    /**
     * This won't work in any IDE Console. <br><br>
     * On Windows: <br>
     * Create a new cmd.exe subprocess <br>
     * Redirect its standard IO to its parent process (this) <br>
     * Execute command "cls" <br>
     * On Linux: <br>
     * Print
     */
    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").toLowerCase().startsWith("win")) { // Environment Variables
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else { // Linux
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}
    }

    private static void endOfRound() {
        if (JAVAGOTCHI.getHasEaten()) {
            JAVAGOTCHI.setHasEaten(false);
            JAVAGOTCHI.setCoins(JAVAGOTCHI.getCoins() + 1);
        }
        else {
            JAVAGOTCHI.setHungerLevel(JAVAGOTCHI.getHungerLevel() + 1);
        }

        if (JAVAGOTCHI.getHasSlept()) {
            JAVAGOTCHI.setHasSlept(false);
            JAVAGOTCHI.setCoins(JAVAGOTCHI.getCoins() + 1);
        }
        else {
            JAVAGOTCHI.setTiredness(JAVAGOTCHI.getTiredness() + 1);
        }

        if ((rounds % 2) == 0) {
            JAVAGOTCHI.setAge(JAVAGOTCHI.getAge() + 1);
        }

        clearConsole();
        rounds++;
    }

    private static void beginOfRound() {
        StringBuilder header = new StringBuilder();
        header.append("************************************* %sRound %s%s *************************************\n".formatted(
                      AnsiColor.CYAN.getCode(), rounds, AnsiColor.RESET.getCode()));
        header.append("### %sName: %s%s ###".formatted(AnsiColor.CYAN.getCode(), JAVAGOTCHI.getName(), AnsiColor.RESET.getCode()));
        header.append(" %sAge: %s%s ###".formatted(AnsiColor.CYAN.getCode(), JAVAGOTCHI.getAge(), AnsiColor.RESET.getCode()));

        int hungerLevel = JAVAGOTCHI.getHungerLevel();
        if (hungerLevel >= Javagotchi.HungerLevel.HIGH.getValue()) {
            header.append(" %sHunger%s ###".formatted(AnsiColor.RED.getCode(), AnsiColor.RESET.getCode()));
        }
        else if (hungerLevel >= Javagotchi.HungerLevel.MEDIUM.getValue()) {
            header.append(" %sHunger%s ###".formatted(AnsiColor.YELLOW.getCode(), AnsiColor.RESET.getCode()));
        }
        else {
            header.append(" %sHunger%s ###".formatted(AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode()));
        }

        int tiredness = JAVAGOTCHI.getTiredness();
        if (tiredness >= Javagotchi.Tiredness.HIGH.getValue()) {
            header.append(" %sTiredness%s ###".formatted(AnsiColor.RED.getCode(), AnsiColor.RESET.getCode()));
        }
        else if (tiredness >= Javagotchi.Tiredness.MEDIUM.getValue()) {
            header.append(" %sTiredness%s ###".formatted(AnsiColor.YELLOW.getCode(), AnsiColor.RESET.getCode()));
        }
        else {
            header.append(" %sTiredness%s ###".formatted(AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode()));
        }

        header.append(" %sCoins: %s%s ###\n".formatted(AnsiColor.CYAN.getCode(), JAVAGOTCHI.getCoins(), AnsiColor.RESET.getCode()));

        System.out.println(header);
        JAVAGOTCHI.printBodyWithMenu();
    }

    private static void gameLoop() {
        splashScreen();

        while (run) {
            if (!JAVAGOTCHI.isAlive()) {
                JAVAGOTCHI.die();
                break;
            }

            beginOfRound();

            if (isFirstRound) {
                JAVAGOTCHI.changeName();
                isFirstRound = false;
                endOfRound();
                continue;
            }

            getUserInput();

            switch (option) {
                case "1":
                    JAVAGOTCHI.greetings();
                    break;
                case "2":
                    JAVAGOTCHI.changeName();
                    break;
                case "3":
                    JAVAGOTCHI.playGames();
                    break;
                case "4":
                    JAVAGOTCHI.playRiskyGames();
                    break;
                case "5":
                    JAVAGOTCHI.feed();
                    break;
                case "6":
                    JAVAGOTCHI.sleep();
                    break;
                case "9":
                    JAVAGOTCHI.goodbye();
                    run = false;
                    break;
                default:
                    JAVAGOTCHI.invalidInput();
                    break;
            }

            endOfRound();
        }
    }

    public static void start() {
        gameLoop();
    }

}
