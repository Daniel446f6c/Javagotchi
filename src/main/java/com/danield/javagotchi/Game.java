package com.danield.javagotchi;


public class Game {

    private Game() {}
    private static final Javagotchi JAVAGOTCHI = new Javagotchi();
    private static final SplashScreen SPLASH_SCREEN = new SplashScreen();
    private static String option;
    private static boolean run = true;
    private static int rounds = 0;
    private static boolean isFirstRound = true;

    private static void endOfRound() {
        if (JAVAGOTCHI.hasEaten()) {
            JAVAGOTCHI.setHasEaten(false);
            JAVAGOTCHI.setCoins(JAVAGOTCHI.getCoins() + 1);
        }
        else {
            JAVAGOTCHI.setHungerLevel(JAVAGOTCHI.getHungerLevel() + 1);
        }

        if (JAVAGOTCHI.hasSlept()) {
            JAVAGOTCHI.setHasSlept(false);
            JAVAGOTCHI.setCoins(JAVAGOTCHI.getCoins() + 1);
        }
        else {
            JAVAGOTCHI.setTiredness(JAVAGOTCHI.getTiredness() + 1);
        }

        if ((rounds % 2) == 0) {
            JAVAGOTCHI.setAge(JAVAGOTCHI.getAge() + 1);
        }

        GameUtils.clearConsole();
        rounds++;
    }

    private static void beginOfRound() {
        StringBuilder header = new StringBuilder();
        header.append("************************************* %sRound %s%s *************************************\n".formatted(
                      AnsiColor.CYAN.getCode(), rounds, AnsiColor.RESET.getCode()));
        header.append("### %sName: %s%s ###".formatted(AnsiColor.CYAN.getCode(), JAVAGOTCHI.getName(), AnsiColor.RESET.getCode()));
        header.append(" %sAge: %s%s ###".formatted(AnsiColor.CYAN.getCode(), JAVAGOTCHI.getAge(), AnsiColor.RESET.getCode()));

        int hungerLevel = JAVAGOTCHI.getHungerLevel();
        if (hungerLevel >= HungerLevel.HIGH.getValue()) {
            header.append(" %sHunger%s ###".formatted(AnsiColor.RED.getCode(), AnsiColor.RESET.getCode()));
        }
        else if (hungerLevel >= HungerLevel.MEDIUM.getValue()) {
            header.append(" %sHunger%s ###".formatted(AnsiColor.YELLOW.getCode(), AnsiColor.RESET.getCode()));
        }
        else {
            header.append(" %sHunger%s ###".formatted(AnsiColor.GREEN.getCode(), AnsiColor.RESET.getCode()));
        }

        int tiredness = JAVAGOTCHI.getTiredness();
        if (tiredness >= Tiredness.HIGH.getValue()) {
            header.append(" %sTiredness%s ###".formatted(AnsiColor.RED.getCode(), AnsiColor.RESET.getCode()));
        }
        else if (tiredness >= Tiredness.MEDIUM.getValue()) {
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
        SPLASH_SCREEN.display();

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

            option = GameUtils.getUserInput("What are we doing next?: ");

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
