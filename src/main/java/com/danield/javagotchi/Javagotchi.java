package com.danield.javagotchi;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Javagotchi {

    private final Random RANDOM = new Random();
    private final Scanner USER_INPUT = new Scanner(System.in);
    private final String BODY_WITH_MENU = "\n" +
            "      /\\_____/\\      (1) Greetings!\n" +
            "     /  o   o  \\     (2) Give me a new name.\n" +
            "    ( ==  ^  == )    (3) Play games with me.\n" +
            "     )         (     (4) Play risky games with me.\n" +
            "    (           )    (5) Give me munchies.\n" +
            "   ( (  )   (  ) )   (6) Go to bed.\n" +
            "  (__(__)___(__)__)  (9) End game.\n\n";
    private final String BODY_HUNGRY_WITH_MENU = "\n" +
            "      /\\_____/\\      (1) Greetings!\n" +
            "     /  o   o  \\     (2) Give me a new name.\n" +
            "    ( ==  ^  == )    (3) Play games with me.\n" +
            "       )     (       (4) Play risky games with me.\n" +
            "      (       )      (5) Give me munchies.\n" +
            "     ( ( ) ( ) )     (6) Go to bed.\n" +
            "    (_(_)___(_)_)    (9) End game.\n\n";
    private final String BODY_TIRED_WITH_MENU = "\n" +
            "      /\\_____/\\      (1) Greetings!\n" +
            "     /  -   -  \\     (2) Give me a new name.\n" +
            "    ( ==  ^  == )    (3) Play games with me.\n" +
            "     )         (     (4) Play risky games with me.\n" +
            "    (           )    (5) Give me munchies.\n" +
            "   ( (  )   (  ) )   (6) Go to bed.\n" +
            "  (__(__)___(__)__)  (9) End game.\n\n";
    private final String BODY_HUNGRY_TIRED_WITH_MENU = "\n" +
            "      /\\_____/\\      (1) Greetings!\n" +
            "     /  -   -  \\     (2) Give me a new name.\n" +
            "    ( ==  ^  == )    (3) Play games with me.\n" +
            "       )     (       (4) Play risky games with me.\n" +
            "      (       )      (5) Give me munchies.\n" +
            "     ( ( ) ( ) )     (6) Go to bed.\n" +
            "    (_(_)___(_)_)    (9) End game.\n\n";
    private final String BODY_DEAD = "\n" +
            "      /\\_____/\\ \n" +
            "     /  X   X  \\ \n" +
            "    ( ==  ^  == ) \n" +
            "     )         ( \n" +
            "    (    XXX    ) \n" +
            "   ( (  )   (  ) ) \n" +
            "  (__(__)___(__)__) \n\n";
    private final String BODY_HUNGRY_DEAD = "\n" +
            "      /\\_____/\\ \n" +
            "     /  X   X  \\ \n" +
            "    ( ==  ^  == ) \n" +
            "       )     ( \n" +
            "      (  XXX  )  \n" +
            "     ( ( ) ( ) ) \n" +
            "    (_(_)___(_)_) \n\n";
    private String name = "-";
    private int age = 1;
    private int hungerLevel = 2;
    private int tiredness = 0;
    private int coins = 0;
    private boolean hasEaten = false;
    private boolean hasSlept = false;

    private void headsOrTails() {
        int javagotchisChoice = RANDOM.nextInt(0, 2);

        GameUtils.animateOutput("""
                Yeah! Let's play something...
                
                We will play "Heads(0) or Tails(1)" :3
                and I've already made a choice!
                I hope you guess it! *happy*
                
                Give it a shot:\s""", 20, false);

        while (!USER_INPUT.hasNextInt()) {
            USER_INPUT.next(); // IMPORTANT! Flush input stream. Breaks program if removed.
            GameUtils.animateOutput("Ups! That's not an number. Try again: ", 20, false);
        }
        int usersGuess = USER_INPUT.nextInt();

        if (javagotchisChoice == usersGuess) {
            int coinsEarned = RANDOM.nextInt(1, 11);
            coins += coinsEarned;
            GameUtils.animateOutput("\n* * * * * CONGRATS!!! * * * * *\n", 20, true);
            GameUtils.animateOutput("You've just earned %s coins!".formatted(coinsEarned), 20, true);
        }
        else {
            if (javagotchisChoice == 0) {
                GameUtils.animateOutput("\nOh no! Sadly, it was Heads...\n", 20, true);
            }
            else {
                GameUtils.animateOutput("\nOh no! Sadly, it was Tails...\n", 20, true);
            }
            GameUtils.animateOutput("I bet you guess it next time! :3", 20, true);
        }
    }

    private void guessTheColor() {
        ArrayList<AnsiColor> colors = new ArrayList<>();
        for (AnsiColor color : AnsiColor.values()) {
            if (color == AnsiColor.RESET) { continue; }
            colors.add(color);
        }

        StringBuilder colorString = new StringBuilder();
        for (AnsiColor color : colors) {
            colorString.append("%s%s%s  ".formatted(color.getCode(), color.getName(), AnsiColor.RESET.getCode()));
        }

        GameUtils.animateOutput("""
                Yeah! Let's play something...
                
                We will play "Guess The Color" :3
                %s
                I hope you guess it! *happy*
                
                Give it a shot:\s""".formatted(colorString), 20, false);

        int javagotchisChoice = RANDOM.nextInt(0, colors.size());

        String usersGuess;
        do {
            usersGuess = USER_INPUT.nextLine();
        } while(usersGuess.isEmpty());

        if (colors.get(javagotchisChoice).getName().equalsIgnoreCase(usersGuess)) {
            int coinsEarned = RANDOM.nextInt(5, 21);
            coins += coinsEarned;
            GameUtils.animateOutput("\n* * * * * CONGRATS!!! * * * * *\n", 20, true);
            GameUtils.animateOutput("You've just earned %s coins!".formatted(coinsEarned), 20, true);
        }
        else {
            GameUtils.animateOutput("Oh no! Sadly, it was %s%s%s\n".formatted(colors.get(javagotchisChoice).getCode(),
                    colors.get(javagotchisChoice).getName(), AnsiColor.RESET.getCode()), 20, true);
            GameUtils.animateOutput("Good luck next time! :3", 20, true);
        }
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getTiredness() {
        return tiredness;
    }

    public boolean hasEaten() {
        return hasEaten;
    }

    public boolean hasSlept() {
        return hasSlept;
    }

    public int getCoins() {
        return coins;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
    }

    public void setHasEaten(boolean hasEaten) {
        this.hasEaten = hasEaten;
    }

    public void setHasSlept(boolean hasSlept) {
        this.hasSlept = hasSlept;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isAlive() {
        return hungerLevel <= HungerLevel.MAX.getValue() && tiredness <= Tiredness.MAX.getValue();
    }

    public void greetings() {
        GameUtils.animateOutput("Hello my friend! I am %s and %s years old. :3".formatted(name, age), 20, true);
    }

    public void printBodyWithMenu() {
        if (hungerLevel >= HungerLevel.MEDIUM.getValue() && tiredness >= Tiredness.MEDIUM.getValue()) {
            System.out.println(BODY_HUNGRY_TIRED_WITH_MENU);
        }
        else if (hungerLevel >= HungerLevel.MEDIUM.getValue()) {
            System.out.println(BODY_HUNGRY_WITH_MENU);
        }
        else if (tiredness >= Tiredness.MEDIUM.getValue()) {
            System.out.println(BODY_TIRED_WITH_MENU);
        }
        else {
            System.out.println(BODY_WITH_MENU);
        }
    }

    public void playGames() {
        if (RANDOM.nextInt(1, 11) <= 5) {
            headsOrTails();
        }
        else {
            guessTheColor();
        }
        tiredness++;
    }

    public void playRiskyGames() {
        int numberLow = RANDOM.nextInt(0, 51);
        int numberHigh = RANDOM.nextInt(51, 101);
        int javagotchisNumber = RANDOM.nextInt(numberLow, numberHigh+1);

        GameUtils.animateOutput("Uhh you seem to love the risk! Let's see how it goes...\n\n" +
                                        "We will play \"Guess the number\" :3\n" +
                                        "and I already chose one between %s and %s !\n".formatted(numberLow, numberHigh) +
                                        "I dare your guess it right! *grrr*\n\n" +
                                        "Give it a shot: ", 20, false);

        while (!USER_INPUT.hasNextInt()) {
            USER_INPUT.next(); // IMPORTANT! Flush input stream. Breaks program if removed.
            GameUtils.animateOutput("Ups! That's not a number. Try again: ", 20, false);
        }
        int usersNumber = USER_INPUT.nextInt();

        if (javagotchisNumber == usersNumber) {
            int coinsEarned = RANDOM.nextInt(20, 61);
            coins += coinsEarned;
            GameUtils.animateOutput("\n* * * * * MEGA CONGRATS!!! * * * * *\n", 20, true);
            GameUtils.animateOutput("You've just earned %s coins!".formatted(coinsEarned), 20, true);
        }
        else {
            GameUtils.animateOutput("\nNO NO NO! Didn't see the %s?...\n".formatted(javagotchisNumber), 20, true);
            GameUtils.animateOutput("Well, good luck next time... ;3", 20, true);
        }

        hungerLevel++;
        tiredness += 2;
    }

    public void changeName() {
        do{
            GameUtils.animateOutput("Oh, I am sooo excited. What's my name gonna be?: ", 20, false);
            name = USER_INPUT.nextLine();
        }
        while (name.isEmpty());

        GameUtils.animateOutput("\n\nSuch a wonderful choice. I am sooo happy! :3", 20, true);
    }

    public void feed() {
        if (hungerLevel <= HungerLevel.MIN.getValue()+1) {
            GameUtils.animateOutput("I'm not hungry right now! :3", 20, true);
            return;
        }

        int rndNum;
        if (hungerLevel < HungerLevel.LOW.getValue()) {
            rndNum = RANDOM.nextInt(1, hungerLevel);
        }
        else if (hungerLevel < HungerLevel.MEDIUM.getValue()) {
            rndNum = RANDOM.nextInt(2, 5);
        }
        else if (hungerLevel < HungerLevel.HIGH.getValue()) {
            rndNum = RANDOM.nextInt(3, 7);
        }
        else {
            rndNum = RANDOM.nextInt(8, 14);
        }

        for (int i = 0; i < rndNum; i++) {
            GameUtils.animateOutput("*munch* ", 20, true);
            hungerLevel--;
        }
        GameUtils.animateOutput("\n\nYUMMY YUMMY YUMMY!! :3", 20 ,true);

        hasEaten = true;
    }

    public void sleep() {
        if (tiredness < Tiredness.LOW.getValue()) {
            GameUtils.animateOutput("I'm not sleepy right now! :3", 20, true);
            return;
        }

        GameUtils.animateOutput("sleeping ", 20, false);
        GameUtils.animateOutput("zZzZzZzZzZzZ\n\n", 400, false);
        GameUtils.animateOutput("Oh wow! Now I'm full of energy! Let's goooo! :3", 20, true);

        tiredness = Tiredness.MIN.getValue();
        hasSlept = true;
    }

    public void die() {
        if (hungerLevel > HungerLevel.MAX.getValue() && tiredness > Tiredness.MAX.getValue()) {
            System.out.println(BODY_HUNGRY_DEAD);
            GameUtils.animateOutput("Wow... ", 20, true);
            System.out.print("\n");
            GameUtils.animateOutput("You have not only managed to let me starve, but also deprived me of sleep... :(", 20, true);
        }
        else if (hungerLevel > HungerLevel.MAX.getValue()) {
            System.out.println(BODY_HUNGRY_DEAD);
            for (int i = 0; i < 3; i++) {
                GameUtils.animateOutput("Why... ", 20, true);
            }
            System.out.print("\n");
            GameUtils.animateOutput("Why haven't you feed me... :(", 20, true);
        }
        else {
            System.out.println(BODY_DEAD);
            for (int i = 0; i < 3; i++) {
                GameUtils.animateOutput("Why... ", 20, true);
            }
            System.out.print("\n");
            GameUtils.animateOutput("Why wasn't I allowed to sleep... :(", 20, true);
        }
    }

    public void goodbye() {
        GameUtils.animateOutput("Thanks for playing. Bye bye! :3", 20, true);
    }

    public void invalidInput() {
        GameUtils.animateOutput("I don't understand you... Sorry... :(", 20, true);
    }

}