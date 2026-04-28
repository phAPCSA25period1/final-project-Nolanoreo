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

    /**
     * Starts the game loop and handles main menu input until the player quits.
     */
    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n==== MAIN MENU ====");
            System.out.println("1. Hatch Egg");
            System.out.println("2. View Pets");
            System.out.println("3. Play With Pet");
            System.out.println("4. Quit");
            System.out.print("Choice: ");
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Egg egg = new Egg();
                        Pet newPet = egg.hatch(scanner);
                        collection.add(newPet);
                        System.out.println("Added " + newPet);
                        break;

                    case 2:
                        viewPets();
                        break;

                    case 3:
                        playWithPet();
                        break;

                    case 4:
                        running = false;
                        System.out.println("Bye Bye!");
                        break;

                    default:
                        System.out.println("Please choose 1-4.");
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
            return;
        }

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(i + ": " + collection.get(i));
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
            System.out.println("1. Fetch (+10)" );
            System.out.println("2. Walk (+5)" );
            System.out.println("3. Rest (+20)" );
            System.out.println("4. Feed (+15)" );
            System.out.println("5. Stop" );

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
                    break;
                case 2:
                    pet.walk();
                    break;
                case 3:
                    pet.rest();
                    break;
                case 4:
                    pet.feed();
                    break;
                case 5:
                    playing = false;
                    continue;
                default:
                    System.out.println("Please choose 1-5.");
                    continue;
            }

            pet.capHappiness();
            pet.displayBar();

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
