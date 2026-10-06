package fhtw.stpro.agentdemo;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String[] WORD_LIST = {
        "elephant", "rocket", "guitar", "pirate", "umbrella",
        "superhero", "cake", "telescope", "jungle", "piano"
    };
    private static final int[] TEAM_SCORES = new int[2];
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        boolean running = true;

        while (running && INPUT.hasNextLine()) {
            System.out.println("=== Charades Main Menu ===");
            System.out.println("1. Start a new round");
            System.out.println("2. Add point for Team 1");
            System.out.println("3. Add point for Team 2");
            System.out.println("4. Show final score and exit");
            System.out.print("Choose an option: ");

            String input = INPUT.nextLine();
            int choice = parseInteger(input);

            if (choice == 1) {
                startNewRound();
            } else if (choice == 2) {
                addPoint(0);
            } else if (choice == 3) {
                addPoint(1);
            } else if (choice == 4) {
                showFinalScore();
                running = false;
            } else {
                System.out.println("Invalid option. Please choose a number from the menu.");
            }
        }
    }

    public static void startNewRound() {
        String randomWord = pickRandomWord();

        System.out.println();
        System.out.println("New round started!");
        System.out.println("Word to act: " + randomWord);
        System.out.println("Press Enter when the round is finished.");

        if (INPUT.hasNextLine()) {
            INPUT.nextLine();
        }

        System.out.println("Which team scored?");
        System.out.println("1. Team 1");
        System.out.println("2. Team 2");
        System.out.println("3. No team");
        System.out.print("Choice: ");

        String teamChoice = INPUT.hasNextLine() ? INPUT.nextLine() : "0";
        int choice = parseInteger(teamChoice);

        if (choice == 1) {
            addPoint(0);
        } else if (choice == 2) {
            addPoint(1);
        } else if (choice == 3) {
            System.out.println("No point awarded.");
        } else {
            System.out.println("Invalid team choice. No point awarded.");
        }

        System.out.println("Current score: Team 1 = " + TEAM_SCORES[0] + ", Team 2 = " + TEAM_SCORES[1]);
        System.out.println();
    }

    public static void addPoint(int teamIndex) {
        TEAM_SCORES[teamIndex] = TEAM_SCORES[teamIndex] + 1;
        System.out.println("Point added to Team " + (teamIndex + 1) + ".");
    }

    public static void showFinalScore() {
        System.out.println();
        System.out.println("=== Final Score ===");
        System.out.println("Team 1: " + TEAM_SCORES[0]);
        System.out.println("Team 2: " + TEAM_SCORES[1]);

        if (TEAM_SCORES[0] > TEAM_SCORES[1]) {
            System.out.println("Winner: Team 1");
        } else if (TEAM_SCORES[1] > TEAM_SCORES[0]) {
            System.out.println("Winner: Team 2");
        } else {
            System.out.println("Draw!");
        }
    }

    public static String pickRandomWord() {
        int index = RANDOM.nextInt(WORD_LIST.length);
        return WORD_LIST[index];
    }

    public static int parseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}