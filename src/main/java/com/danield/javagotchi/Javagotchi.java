package com.danield.javagotchi;

import java.util.ArrayList;
import java.util.Arrays;
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

    private void animateOutput(String message, int millis, boolean shortSleepAfter) {
        if (millis < 0) throw new IllegalArgumentException("millis must be positive");
        if (message.isEmpty()) throw new IllegalArgumentException("message must not be empty");
        try {
            for (int i = 0; i < message.length(); i++) {
                Thread.sleep(millis);
                System.out.print(message.charAt(i));
            }
            if (shortSleepAfter) { Thread.sleep(1000); }
        } catch (InterruptedException ignored) {}
    }

    private void headsOrTails() {
        int javagotchisChoice = RANDOM.nextInt(0, 2);

        animateOutput("""
                Yeah! Let's play something...
                
                We will play "Heads(0) or Tails(1)" :3
                and I've already made a choice!
                I hope you guess it! *happy*
                
                Give it a shot:\s""", 20, false);

        while (!USER_INPUT.hasNextInt()) {
            USER_INPUT.next(); // IMPORTANT! Flush input stream. Breaks program if removed.
            animateOutput("Ups! That's not an number. Try again: ", 20, false);
        }
        int usersGuess = USER_INPUT.nextInt();

        if (javagotchisChoice == usersGuess) {
            int coinsEarned = RANDOM.nextInt(1, 11);
            coins += coinsEarned;
            animateOutput("\n* * * * * CONGRATS!!! * * * * *\n", 20, true);
            animateOutput("You've just earned %s coins!".formatted(coinsEarned), 20, true);
        }
        else {
            if (javagotchisChoice == 0) {
                animateOutput("\nOh no! Sadly, it was Heads...\n", 20, true);
            }
            else {
                animateOutput("\nOh no! Sadly, it was Tails...\n", 20, true);
            }
            animateOutput("I bet you guess it next time! :3", 20, true);
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

        animateOutput("""
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
            animateOutput("\n* * * * * CONGRATS!!! * * * * *\n", 20, true);
            animateOutput("You've just earned %s coins!".formatted(coinsEarned), 20, true);
        }
        else {
            animateOutput("Oh no! Sadly, it was %s%s%s\n".formatted(colors.get(javagotchisChoice).getCode(),
                    colors.get(javagotchisChoice).getName(), AnsiColor.RESET.getCode()), 20, true);
            animateOutput("Good luck next time! :3", 20, true);
        }
    }

    public enum HungerLevel {
        MIN(0),
        LOW(5),
        MEDIUM(10),
        HIGH(18),
        MAX(20);

        private final int num;

        HungerLevel(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }
    }

    public enum Tiredness {
        MIN(0),
        LOW(5),
        MEDIUM(10),
        HIGH(18),
        MAX(20);

        private final int num;

        Tiredness(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
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

    public boolean getHasEaten() {
        return hasEaten;
    }

    public boolean getHasSlept() {
        return hasSlept;
    }

    public int getCoins() {
        return coins;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
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
        animateOutput("Hello my friend! I am %s and %s years old. :3".formatted(name, age), 20, true);
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

        animateOutput("Uhh you seem to love the risk! Let's see how it goes...\n\n" +
                          "We will play \"Guess the number\" :3\n" +
                          "and I already chose one between %s and %s !\n".formatted(numberLow, numberHigh) +
                          "I dare your guess it right! *grrr*\n\n" +
                          "Give it a shot: ", 20, false);

        while (!USER_INPUT.hasNextInt()) {
            USER_INPUT.next(); // IMPORTANT! Flush input stream. Breaks program if removed.
            animateOutput("Ups! That's not a number. Try again: ", 20, false);
        }
        int usersNumber = USER_INPUT.nextInt();

        if (javagotchisNumber == usersNumber) {
            int coinsEarned = RANDOM.nextInt(20, 61);
            coins += coinsEarned;
            animateOutput("\n* * * * * MEGA CONGRATS!!! * * * * *\n", 20, true);
            animateOutput("You've just earned %s coins!".formatted(coinsEarned), 20, true);
        }
        else {
            animateOutput("\nNO NO NO! Didn't see the %s?...\n".formatted(javagotchisNumber), 20, true);
            animateOutput("Well, good luck next time... ;3", 20, true);
        }

        hungerLevel++;
        tiredness += 2;
    }

    public void changeName() {
        do{
            animateOutput("Oh, I am sooo excited. What's my name gonna be?: ", 20, false);
            name = USER_INPUT.nextLine();
        }
        while (name.isEmpty());

        animateOutput("\n\nSuch a wonderful choice. I am sooo happy! :3", 20, true);
    }

    public void feed() {
        if (hungerLevel <= HungerLevel.MIN.getValue()) {
            animateOutput("I'm not hungry right now! :3", 20, true);
            return;
        }

        for (int i = 0; i < 3; i++) {
            animateOutput("*munch* ", 20, true);
        }

        animateOutput("\n\nYUMMY YUMMY YUMMY!! :3", 20 ,true);

        hungerLevel--;
        hasEaten = true;
    }

    public void sleep() {
        if (tiredness < Tiredness.LOW.getValue()) {
            animateOutput("I'm not sleepy right now! :3", 20, true);
            return;
        }

        animateOutput("sleeping ", 20, false);
        animateOutput("zZzZzZzZzZzZ\n\n", 400, false);
        animateOutput("Oh wow! Now I'm full of energy! Let's goooo! :3", 20, true);

        tiredness = Tiredness.MIN.getValue();
        hasSlept = true;
    }

    public void die() {
        if (hungerLevel > HungerLevel.MAX.getValue() && tiredness > Tiredness.MAX.getValue()) {
            System.out.println(BODY_HUNGRY_DEAD);
            animateOutput("Wow... ", 20, true);
            System.out.print("\n");
            animateOutput("You have not only managed to let me starve, but also deprived me of sleep... :(", 20, true);
        }
        else if (hungerLevel > HungerLevel.MAX.getValue()) {
            System.out.println(BODY_HUNGRY_DEAD);
            for (int i = 0; i < 3; i++) {
                animateOutput("Why... ", 20, true);
            }
            System.out.print("\n");
            animateOutput("Why haven't you feed me... :(", 20, true);
        }
        else {
            System.out.println(BODY_DEAD);
            for (int i = 0; i < 3; i++) {
                animateOutput("Why... ", 20, true);
            }
            System.out.print("\n");
            animateOutput("Why wasn't I allowed to sleep... :(", 20, true);
        }
    }

    public void goodbye() {
        animateOutput("Thanks for playing. Bye bye! :3", 20, true);
    }

    public void invalidInput() {
        animateOutput("I don't understand you... Sorry... :(", 20, true);
    }

}
