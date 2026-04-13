import java.util.Random;
import java.util.Scanner;

/**
 * A simple console-based program that simulates rolling for a random pet
 * in a adopt me–themed system. Each pet has a different probability
 * of appearing. The user may reroll up to three times before accepting
 * a final pet.
 */
public class Eggchance {

    /**
     * The main entry point of the program. Allows the user to roll for a pet,
     * optionally reroll up to three times, and displays the final result.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Pets and their associated probabilities
        String[] pets = {"Common pet 60%", "Uncommon pet 30%", "Rare pet 6%", "Ultra rare pet 3%", "Legendary pet 1%"};
        int[] chances = {60, 30, 6, 3, 1};

        int rerolls = 3;
        String pet;

        System.out.println("Welcome to Egg!");
        System.out.println("Roll a random pet. You have 3 rerolls.");

        // --- REROLL LOOP ---
        while (true) {

            pet = getRandomPet(random, pets, chances);
            System.out.println("You rolled: " + pet);

            if (rerolls == 0) {
                System.out.println("No rerolls left.");
                break;
            }

            // Ask user if they want to reroll
            System.out.print("Reroll? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();

            while (!choice.equals("yes") && !choice.equals("no")) {
                System.out.print("Please type yes or no: ");
                choice = scanner.nextLine().toLowerCase();
            }

            if (choice.equals("no")) {
                break;   // User accepts the pet
            }

            rerolls--;   // Use one reroll
        }

        System.out.println("Your final pet is: " + pet);
        scanner.close();
    }

    /**
     * Selects a random pet based on weighted probabilities.
     *
     * @param random  a {@link Random} instance used for generating random numbers
     * @param pets    an array of pet names
     * @param chances an array of corresponding percentage chances for each pet
     * @return a randomly selected pet from the given list based on weights
     */
    private static String getRandomPet(Random random, String[] pets, int[] chances) {

        int roll = random.nextInt(100) + 1;   // Random number from 1 to 100
        int sum = 0;

        for (int i = 0; i < pets.length; i++) {
            sum += chances[i];
            if (roll <= sum) {
                return pets[i];
            }
        }

        return "Error"; // Safety fallback (should never occur)
    }
}
