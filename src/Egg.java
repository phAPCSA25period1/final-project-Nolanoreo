import java.util.Random;
import java.util.Scanner;

/**
 * Represents an egg that can be raised until it hatches into a pet.
 * The egg gains happiness from player actions and then generates a pet
 * with a random rarity and type.
 */
public class Egg {

    private int happiness;
    private final int MAX_HAPPINESS = 100;
    private String eggType;

    private static Random rand = new Random();

    private static String[][] pets = {
            { "Dog", "Cat", "Buffalo", "Otter", "Robin" },
            { "Fennec Fox", "Puma", "Snow Cat", "Chocolate Labrador", "Dingo" },
            { "Beaver", "Bunny", "Monkey", "Rabbit", "Snow Puma" },
            { "Red Panda", "Shiba Inu", "Frog", "Penguin", "Koala" },
            { "Dragon", "Unicorn", "Griffin", "Kangaroo", "Turtle" }
    };

    private static String[] rarityNames = {
            "Common", "Uncommon", "Rare", "Ultra-Rare", "Legendary"
    };

    /**
     * Raises the egg's happiness until it reaches the hatch threshold.
     * Prompts the user for actions, displays the growth bar, then
     * creates and returns a newly hatched pet.
     *
     * @param scanner the scanner to read user choices and pet name input
     * @return the newly hatched Pet
     */
    public Egg() {
        this("Common Egg");
    }

    public Egg(String eggType) {
        this.eggType = eggType;
    }

    public Pet hatch(Scanner scanner) {

        happiness = 0;

        System.out.println("\nRaise your egg's happiness to hatch it!");
        System.out.println("Egg type: " + eggType);

        while (happiness < MAX_HAPPINESS) {

            System.out.println("\n1. Play (+10)");
            System.out.println("2. Move (+5)");
            System.out.println("3. Rest (+15)");
            System.out.println("4. Do nothing (+2)");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    happiness += 10;
                    break;
                case 2:
                    happiness += 5;
                    break;
                case 3:
                    happiness += 15;
                    break;
                case 4:
                    happiness += 2;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            if (happiness > MAX_HAPPINESS) {
                happiness = MAX_HAPPINESS;
            }

            displayBar();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }

        System.out.println("\nYour egg is hatching...");

        int rarityIndex = rollStarterRarity();
        String type = getRandomPet(rarityIndex);
        String rarity = rarityNames[rarityIndex];

        System.out.println("\nRarity: " + rarity);
        System.out.println("You got: " + type);

        scanner.nextLine();
        System.out.print("Name your " + type + ": ");
        String name = scanner.nextLine();

        return new Pet(name, type, rarity);
    }

    /**
     * Determines the rarity index for a new pet using a random roll.
     * The returned index corresponds to the rarity tier arrays.
     *
     * @return the rarity index for the hatched pet
     */
    private int rollStarterRarity() {
        int roll = rand.nextInt(100) + 1;
        int rarity;

        if (eggType.equals("Rare Egg")) {
            if (roll <= 55)
                rarity = 0;
            else if (roll <= 85)
                rarity = 1;
            else if (roll <= 95)
                rarity = 2;
            else if (roll <= 99)
                rarity = 3;
            else
                rarity = 4;
            // Increase rarity by one level for rare eggs
            rarity += 1;
            if (rarity > 4) {
                rarity = 4;
            }
        } else if (eggType.equals("Uncommon Egg")) {
            if (roll <= 60)
                rarity = 1;
            else if (roll <= 88)
                rarity = 2;
            else if (roll <= 97)
                rarity = 3;
            else
                rarity = 4;
        } else {
            // Common eggs keep original distribution
            if (roll <= 70)
                rarity = 0;
            else if (roll <= 92)
                rarity = 1;
            else if (roll <= 98)
                rarity = 2;
            else if (roll <= 100)
                rarity = 3;
            else
                rarity = 4;
        }

        return rarity;
    }

    private String getRandomPet(int rarityIndex) {
        int index = rand.nextInt(pets[rarityIndex].length);
        return pets[rarityIndex][index];
    }

    /**
     * Displays the current happiness level as a text progress bar.
     */
    private void displayBar() {
        int bars = happiness / 10;

        System.out.print("Happiness: [");
        for (int i = 0; i < bars; i++)
            System.out.print("#");
        for (int i = bars; i < 10; i++)
            System.out.print("-");
        System.out.println("] " + happiness + "/100");
    }
}
