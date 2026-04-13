import java.util.Random;
import java.util.Scanner;

/**
 * The main entry point for the adopt me–style character game.
 * <p>
 * Players roll for a random character based on weighted probabilities.
 * They may reroll up to three times, then name their character and
 * control it through actions such as attacking, moving, resting, or doing nothing.
 * The game ends when the player exits.
 */
public class main {

    /**
     * Launches the program, handles the reroll system, character creation,
     * and main gameplay loop.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Pet names and their probabilities
        String[] pets = {"Common egg 60%", "Uncommon 30%", "Rare egg 6%", "Ultra rare egg 3%", "Legendary Egg 1%"};
        int[] chances = {60, 30, 6, 3, 1};

        int rerolls = 3;
        String chosenPet = "";

        System.out.println("Welcome to the game!");
        System.out.println("You will roll for a random character. You have up to 3 rerolls.");

        // Reroll system
        while (true) {
            chosenPet = rollPet(random, pets, probabilities);
            System.out.println("You rolled: " + chosenPet);

            if (rerolls == 0) {
                System.out.println("No rerolls left.");
                break;
            }

            System.out.print("Do you want to reroll? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();

            while (!response.equals("yes") && !response.equals("no")) {
                System.out.print("Please type ONLY 'yes' or 'no': ");
                response = scanner.nextLine().toLowerCase();
            }

            if (response.equals("no")) {
                break;
            }

            rerolls--;
        }

        // Create the chosen character
        Character character = createCharacter(chosenPet, scanner);

        if (character == null) {
            System.out.println("Error: Could not create character.");
            return;
        }

        System.out.println("\nYou have chosen: " + character.getName());
        character.displayInfo();

        // Game loop
        while (character.getHealth() > 0) {

            System.out.println("\nWhat should your character do?");
            System.out.println("1. Attack");
            System.out.println("2. Move");
            System.out.println("3. Rest");
            System.out.println("4. Do Nothing");
            System.out.println("5. Exit Game");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Perform the chosen action
            boolean exitGame = handleAction(choice, character, scanner);
            if (exitGame) return;

            character.displayInfo();

            if (character.getHealth() <= 0) {
                System.out.println("\n" + character.getName() + " has died. Game over!");
                break;
            }
        }

        scanner.close();
    }

    /**
     * Rolls a random pet based on weighted probability.
     *
     * @param random        a {@link Random} object for random number generation
     * @param pets          an array of pet names
     * @param probabilities an array of corresponding probability weights
     * @return the name of the randomly selected pet
     */
    private static String rollPet(Random random, String[] pets, int[] probabilities) {
        int roll = random.nextInt(100) + 1;
        int cumulative = 0;

        for (int i = 0; i < pets.length; i++) {
            cumulative += probabilities[i];
            if (roll <= cumulative) {
                return pets[i];
            }
        }
        return "Error";
    }

    /**
     * Creates a character object based on the chosen pet string,
     * while also asking the user to name the character.
     *
     * @param chosenPet the pet name rolled by the player
     * @param scanner   a scanner for reading user input
     * @return the created {@link Character} object, or null if invalid
     */
    private static Character createCharacter(String chosenPet, Scanner scanner) {

        switch (chosenPet) {
            case "Dart Goblin 50%":
                System.out.println("Name your Dart Goblin:");
                return new DartGoblin(scanner.nextLine());

            case "HogRider 35%":
                System.out.println("Name your Hog Rider:");
                return new HogRider(scanner.nextLine());

            case "Beserker 14%":
                System.out.println("Name your Beserker:");
                return new Beserker(scanner.nextLine());

            case "Pekka 1%":
                System.out.println("Name your Pekka:");
                return new pekka(scanner.nextLine());

            default:
                System.out.println("Invalid character type: " + chosenPet);
                return null;
        }
    }

    /**
     * Handles user actions inside the gameplay loop.
     *
     * @param choice    the selected action (1–5)
     * @param character the player's current character
     * @param scanner   a scanner for user input
     * @return true if the player chooses to exit the game, otherwise false
     */
    private static boolean handleAction(int choice, Character character, Scanner scanner) {

        switch (choice) {
            case 1 -> character.attack();
            case 2 -> character.move();
            case 3 -> character.rest();
            case 4 -> character.doNothing();
            case 5 -> {
                System.out.println("Exiting the game. Goodbye!");
                scanner.close();
                return true; // stop the game
            }
            default -> System.out.println("Invalid choice. Try again.");
        }

        return false; // continue game
    }
}
