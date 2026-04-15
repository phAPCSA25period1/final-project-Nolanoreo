import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();

    // 2D array: [rarity][pet]
    static String[][] pets = {
            { "Dog", "Cat", "Buffalo", "Otter", "Robin" }, // Common
            { "Fennec Fox", "Puma", "Snow Cat", "Chocolate Labrador", "Dingo" }, // Uncommon
            { "Beaver", "Bunny", "Monkey", "Rabbit", "Snow Puma" }, // Rare
            { "Red Panda", "Shiba Inu", "Frog", "Penguin", "Koala" }, // Ultra-Rare
            { "Dragon", "Unicorn", "Griffin", "Kangaroo", "Turtle" } // Legendary
    };

    static String[] rarityNames = {
            "Common", "Uncommon", "Rare", "Ultra-Rare", "Legendary"
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int happiness = 0;
        final int MAX_HAPPINESS = 100;

        System.out.println("Welcome to the Egg Hatching Game!");
        System.out.println("Raise your egg's happiness to hatch it.\n");

        // GAME LOOP
        while (happiness < MAX_HAPPINESS) {

            System.out.println("\nChoose an action:");
            System.out.println("1. Play (+10)");
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
                    break;
            }

            if (happiness > MAX_HAPPINESS) {
                happiness = MAX_HAPPINESS;
            }

            displayBar(happiness);

        }

        // HATCHING
        System.out.println("\n🐣 Your egg is hatching...");

        int rarityIndex = rollStarterRarity();
        String pet = getRandomPet(rarityIndex);

        System.out.println("Rarity: " + rarityNames[rarityIndex]);
        System.out.println("You got: " + pet);

        // NAME PET
        scanner.nextLine(); // clear buffer
        System.out.print("Name your " + pet + ": ");
        String name = scanner.nextLine();

        System.out.println("\n🎉 " + name + " the " + pet + " is ready!");

        scanner.close();
    }

    // 🎲 Roll rarity (starter odds)
    static int rollStarterRarity() {
        int roll = rand.nextInt(100) + 1;

        if (roll <= 70)
            return 0; // Common
        else if (roll <= 92)
            return 1; // Uncommon
        else if (roll <= 98)
            return 2; // Rare
        else if (roll <= 100)
            return 3; // Ultra-Rare
        else
            return 4; // Legendary (basically unreachable)
    }

    // 🎯 Pick random pet from rarity
    static String getRandomPet(int rarityIndex) {
        int index = rand.nextInt(pets[rarityIndex].length);
        return pets[rarityIndex][index];
    }

    // 📊 Display happiness bar
    static void displayBar(int happiness) {
        int bars = happiness / 10;

        System.out.print("Happiness: [");
        for (int i = 0; i < bars; i++)
            System.out.print("#");
        for (int i = bars; i < 10; i++)
            System.out.print("-");
        System.out.println("] " + happiness + "/100");
    }
}
