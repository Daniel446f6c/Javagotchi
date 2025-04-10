package com.danield.javagotchi.game;

import com.danield.javagotchi.utils.AnsiColor;
import com.danield.javagotchi.utils.GameUtils;

public class SplashScreen implements Displayable {

    private final String SPLASH_IMAGE;

    private void splash() {
        for (int i = 0; i < 6; i++) {
            for (AnsiColor color : AnsiColor.values()) {
                if (color.equals(AnsiColor.RESET)) continue;
                GameUtils.clearConsole();
                System.out.print(color.getCode() + SPLASH_IMAGE);
                try {
                    Thread.sleep(120);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public SplashScreen() {
        SPLASH_IMAGE = """
                
                
                
                \s\s\s\s\s\s  ________     ____     __    __     ____        _____      ____     ________     ____   __    __    _____\s
                \s\s\s\s\s\s (___  ___)   (    )    ) )  ( (    (    )      / ___ \\    / __ \\   (___  ___)   / ___) (  \\  /  )  (_   _)
                \s\s\s\s\s\s     ) )      / /\\ \\   ( (    ) )   / /\\ \\     / /   \\_)  / /  \\ \\      ) )     / /      \\ (__) /     | | \s
                \s\s\s\s\s\s    ( (      ( (__) )   \\ \\  / /   ( (__) )   ( (  ____  ( ()  () )    ( (     ( (        ) __ (      | | \s
                \s\s\s\s\s\s __  ) )      )    (     \\ \\/ /     )    (    ( ( (__  ) ( ()  () )     ) )    ( (       ( (  ) )     | | \s
                \s\s\s\s\s\s( (_/ /      /  /\\  \\     \\  /     /  /\\  \\    \\ \\__/ /   \\ \\__/ /     ( (      \\ \\___    ) )( (     _| |_
                \s\s\s\s\s\s \\___/      /__(  )__\\     \\/     /__(  )__\\    \\____/     \\____/      /__\\      \\____)  /_/  \\_\\   /_____\\
                
                
                
                                     ▄▀░░█                                                                    ▄▀▀▀▀█
                                   ▄▀█░░░█                                                                   █▀░░░░█
                                ▄▀▀▒█▒░░░█                                                                  █░░░░░░█▀▀▀▄▄      ▓
                     ▄▀▀▄   ▄▄▀▀▒▒▒▒█▒▒░░█                                                                 ▄█░░░░░░░░░░░░▀▀▄▄ ▓▓▓
                    █▒░░░▀▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒█                                                                ▄█░░░░░░░░░░░░░░░░░▀▓▓▓▓▀▀▀█
                    █▒░░░░▒▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄                                                           ▀▄▄█░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓░░█
                    █▒░░░░░▒▒▒▒▒▒▒▒▒█▒█▒▒▒▒▒▀▄                                                         ▀▄█░▀▄░░░░▄▄░░░░░░▓▓▓▓▓▓▓░░▓▓▓▓▓▓
                    █▀▄░░▒▒▒▒▒▒▒▒█▒▒▒█▒█▒▄▄▒▒█                                                         ▄▀█▀▄░░░░███░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓
                   ██▒▒▀▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒█▄██▒▒█                                                         ▀█▀▄░░░░▀▀░░░░░░░░░▄▄░░░▓▓▓▓▓
                 ▄▀▒█▒▒▒▒▒▒▒▒▒▒▒▄▀██▒▒▒▒▒▀▀▒▒█░░░▄                                                       ▀█░░░░░░░░▄▄░░░░░███░░░░▓▓░█
                ▀▒▒▒▒█▒▒▒▒▒▒▒▄▒█████▄▒▒▒▒▒▒▒▄▀▀▀▀                                                         ▀█░░░░░░█░░▀▄░░░▀▀░░░░░▓░█▀
                ▒▒▒▒▒█▒▒▒▒▒▄▀▒▒▒▀▀▀▒▒▒▒▄█▀░░▒█▀▀▄▄                                                         ▀█░░░░░░▀▄▄▀░░░░░░░░▀▄░█▀
                ▒▒▒▒▒▒█▒▄▄▀▒▒▒▒▒▒▒▒▒▒▒░░█▒▀▄▀▄░░░░▀                                                          ▀█▄░░░░░░░░░░░░░▀▄░░██
                ▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▄▒▒▒▒▄▀▒▒▒█░░▀▄                                                                ▀█▄░░░░░░░░▀▄░░██▀░▀
                ▒▒▒▒▒▒▒▒▀▄▒▒▒▒▒▒▒▒▀▀▀▀▒▒▒▄▀                                                                        ▀▀▄▄▄▄▄▄▄█▀█░░▀▄▀
                """;
    }

    @Override
    public void display() {
        splash();
        System.out.print(AnsiColor.RESET.getCode());
        GameUtils.clearConsole();
    }
}
