import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Manages the pet game loop, including hatching eggs, showing the pet
 * collection, and playing with pets.
 */
public class Game {

    private ArrayList<Pet> collection = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int coins = 100;
    private int food = 0;
    private int toys = 0;
    private ArrayList<Quest> quests = new ArrayList<>();

    /**
     * Starts the game loop and handles main menu input until the player quits.
     */
    public void start() {

        boolean running = true;

        initQuests();

        while (running) {

            System.out.println("\n==== MAIN MENU ====");
            System.out.println("Coins: " + coins);
            System.out.println("1. Hatch Egg");
            System.out.println("2. View Pets");
            System.out.println("3. Play With Pet");
            System.out.println("4. Shop");
            System.out.println("5. Inventory");
            System.out.println("6. Quests");
            System.out.println("7. Quit");
            System.out.print("Choice: ");
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Egg egg = new Egg();
                        Pet newPet = egg.hatch(scanner);
                        collection.add(newPet);
                        coins += 10;
                        System.out.println("Added " + newPet + " and earned 10 coins!");
                        updateQuest("Hatch 1 egg", 1);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        break;

                    case 2:
                        viewPets();
                        break;

                    case 3:
                        playWithPet();
                        break;

                    case 4:
                        shop();
                        break;

                    case 5:
                        inventory();
                        break;

                    case 6:
                        showQuests();
                        break;

                    case 7:
                        running = false;
                        System.out.println("Bye Bye!");
                        break;

                    default:
                        System.out.println("Please choose 1-7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine(); // clear the bad token
            }
        }

        scanner.close();
    }
    // ArrayList and seeing if there are any pets in it, if not then

    /**
     * Prints the current list of pets in the collection.
     * If there are no pets, displays a message instead.
     */
    private void viewPets() {
        if (collection.isEmpty()) {
            System.out.println("No pets yet!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            return;
        }

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(i + ": " + collection.get(i));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    private void inventory() {
        System.out.println("\n==== INVENTORY ====");
        System.out.println("Coins: " + coins);
        System.out.println("Food: " + food);
        System.out.println("Toys: " + toys);
        System.out.println("\nPets:");
        if (collection.isEmpty()) {
            System.out.println("No pets yet!");
        } else {
            for (int i = 0; i < collection.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + collection.get(i));
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    private void initQuests() {
        quests.add(new Quest("Hatch 1 egg", 15, 1));
        quests.add(new Quest("Buy 1 toy", 10, 1));
        quests.add(new Quest("Play with a pet 3 times", 20, 3));
    }

    private void showQuests() {
        System.out.println("\n==== QUESTS ====");
        for (int i = 0; i < quests.size(); i++) {
            System.out.println((i + 1) + ". " + quests.get(i));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    private void updateQuest(String description, int amount) {
        for (Quest quest : quests) {
            if (!quest.isComplete() && quest.getDescription().equals(description)) {
                quest.advance(amount);
                if (quest.isComplete()) {
                    coins += quest.getReward();
                    System.out.println("Quest complete! +" + quest.getReward() + " coins");
                }
            }
        }
    }

    private class Quest {
        private String description;
        private int reward;
        private int progress;
        private int goal;

        public Quest(String description, int reward, int goal) {
            this.description = description;
            this.reward = reward;
            this.goal = goal;
            this.progress = 0;
        }

        public String getDescription() {
            return description;
        }

        public int getReward() {
            return reward;
        }

        public boolean isComplete() {
            return progress >= goal;
        }

        public void advance(int amount) {
            progress += amount;
            if (progress > goal) {
                progress = goal;
            }
        }

        public String toString() {
            if (isComplete()) {
                return description + " - Completed! (" + reward + " coins)";
            }
            return description + " (" + progress + "/" + goal + ") - " + reward + " coins";
        }
    }

    private void shop() {
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n==== SHOP ====");
            System.out.println("Coins: " + coins);
            System.out.println("1. Common Egg (50 coins)");
            System.out.println("2. Rare Egg (100 coins)");
            System.out.println("3. Food (+1) (20 coins)");
            System.out.println("4. Toy (+1) (30 coins)");
            System.out.println("5. Back");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    buyEgg("Common Egg", 50);
                    break;
                case 2:
                    buyEgg("Rare Egg", 100);
                    break;
                case 3:
                    buyItem("Food", 20);
                    break;
                case 4:
                    buyItem("Toy", 30);
                    break;
                case 5:
                    shopping = false;
                    break;
                default:
                    System.out.println("Please choose 1-5.");
            }
        }
    }

    private void buyEgg(String eggType, int price) {
        if (coins < price) {
            System.out.println("Not enough coins for " + eggType + ".");
            return;
        }

        coins -= price;
        System.out.println("Bought " + eggType + " for " + price + " coins.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        Egg egg = new Egg(eggType);
        Pet newPet = egg.hatch(scanner);
        collection.add(newPet);
        coins += 10;
        System.out.println("Added " + newPet + " and earned 10 coins!");
        updateQuest("Hatch 1 egg", 1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    private void buyItem(String itemName, int price) {
        if (coins < price) {
            System.out.println("Not enough coins for " + itemName + ".");
            return;
        }

        coins -= price;
        if (itemName.equals("Food")) {
            food++;
        } else if (itemName.equals("Toy")) {
            toys++;
        }
        System.out.println("Bought 1 " + itemName + ".");
        if (itemName.equals("Toy")) {
            updateQuest("Buy 1 toy", 1);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Allows the player to choose a pet and perform actions to increase
     * that pet's happiness until the player stops or the pet reaches max
     * happiness.
     */
    private void playWithPet() {

        if (collection.isEmpty()) {
            System.out.println("No pets to play with!");
            return;
        }

        viewPets();
        System.out.print("Choose a pet: ");

        int index;
        try {
            index = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a number.");
            scanner.nextLine();
            return;
        }

        if (index < 0 || index >= collection.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Pet pet = collection.get(index);
        boolean playing = true;

        while (playing) {

            System.out.println("\nPlaying with " + pet.getName());
            System.out.println("1. Fetch (+10)");
            System.out.println("2. Walk (+5)");
            System.out.println("3. Rest (+20)");
            System.out.println("4. Feed (+15) [Food: " + food + "]");
            System.out.println("5. Give Toy (+25) [Toys: " + toys + "]");
            System.out.println("6. Stop");

            int action;
            try {
                action = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (action) {
                case 1:
                    pet.fetch();
                    coins += 5;
                    updateQuest("Play with a pet 3 times", 1);
                    break;
                case 2:
                    pet.walk();
                    coins += 2;
                    updateQuest("Play with a pet 3 times", 1);
                    break;
                case 3:
                    pet.rest();
                    coins += 3;
                    updateQuest("Play with a pet 3 times", 1);
                    break;
                case 4:
                    if (food <= 0) {
                        System.out.println("You have no food. Buy some in the shop.");
                        continue;
                    }
                    pet.feed();
                    food--;
                    coins += 1;
                    updateQuest("Play with a pet 3 times", 1);
                    break;
                case 5:
                    if (toys <= 0) {
                        System.out.println("You have no toys. Buy one in the shop.");
                        continue;
                    }
                    pet.playWithToy();
                    toys--;
                    coins += 2;
                    updateQuest("Play with a pet 3 times", 1);
                    break;
                case 6:
                    playing = false;
                    continue;
                default:
                    System.out.println("Please choose 1-6.");
                    continue;
            }

            pet.capHappiness();
            pet.displayBar();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            if (pet.isMaxHappiness()) {
                System.out.println("1. Keep\n2. Release pet");
                int decision;
                try {
                    decision = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Enter a number.");
                    scanner.nextLine();
                    continue;
                }

                if (decision == 2) {
                    collection.remove(index);
                    playing = false;
                }
            }
        }
    }
}
