package com.danield.javagotchi.utils;

import java.io.IOException;
import java.util.Scanner;

public class GameUtils {

    private GameUtils() {}
    private static final Scanner USER_INPUT = new Scanner(System.in);

    public static String getUserInputString() {
        return USER_INPUT.nextLine().strip();
    }

    public static String getUserInputString(String prompt) {
        animateOutput(prompt, 20);
        return USER_INPUT.nextLine().strip();
    }

    public static void animateOutput(String message, int millis) {
        if (millis < 0) throw new IllegalArgumentException("millis must be positive");
        if (message.isEmpty()) throw new IllegalArgumentException("message must not be empty");
        try {
            for (int i = 0; i < message.length(); i++) {
                Thread.sleep(millis);
                System.out.print(message.charAt(i));
            }
        } catch (InterruptedException ignored) {}
    }

    public static void animateOutput(String message, int millis, int millisToSleepAfter) {
        if (millis < 0) throw new IllegalArgumentException("millis must be positive");
        if (millisToSleepAfter < 0) throw new IllegalArgumentException("millisToSleepAfter must be positive");
        if (message.isEmpty()) throw new IllegalArgumentException("message must not be empty");
        try {
            for (int i = 0; i < message.length(); i++) {
                Thread.sleep(millis);
                System.out.print(message.charAt(i));
            }
            Thread.sleep(millisToSleepAfter);
        } catch (InterruptedException ignored) {}
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
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").toLowerCase().startsWith("win")) { // Environment Variables
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else { // Linux
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}
    }

}
