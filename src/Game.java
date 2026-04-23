import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Pet> collection = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n==== MAIN MENU ====");
            System.out.println("1. Hatch Egg");
            System.out.println("2. View Pets");
            System.out.println("3. Play With Pet");
            System.out.println("4. Quit");
            System.out.print("Choice: ");

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
            }
        }

        scanner.close();
    }

    private void viewPets() {
        if (collection.isEmpty()) {
            System.out.println("No pets yet!");
            return;
        }

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(i + ": " + collection.get(i));
        }
    }

    private void playWithPet() {

        if (collection.isEmpty()) {
            System.out.println("No pets to play with!");
            return;
        }

        viewPets();
        System.out.print("Choose a pet: ");
        int index = scanner.nextInt();

        if (index < 0 || index >= collection.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Pet pet = collection.get(index);
        boolean playing = true;

        while (playing) {

            System.out.println("\nPlaying with " + pet.getName());
            System.out.println("1. Fetch");
            System.out.println("2. Walk");
            System.out.println("3. Rest");
            System.out.println("4. Feed");
            System.out.println("5. Stop");

            int action = scanner.nextInt();

            switch (action) {
                case 1: pet.fetch(); break;
                case 2: pet.walk(); break;
                case 3: pet.rest(); break;
                case 4: pet.feed(); break;
                case 5: playing = false; continue;
            }

            pet.capHappiness();
            pet.displayBar();

            if (pet.isMaxHappiness()) {
                System.out.println("1. Keep\n2. Remove");
                int decision = scanner.nextInt();

                if (decision == 2) {
                    collection.remove(index);
                    playing = false;
                }
            }
        }
    }
}
