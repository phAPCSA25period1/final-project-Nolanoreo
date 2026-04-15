import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Egg egg = new Egg();

        System.out.println("Welcome to the Egg Hatching Game!");
        System.out.println("Take care of your egg until it hatches.\n");

        String pet = null;

        // GAME LOOP (before hatch)
        while (!egg.isReadyToHatch()) {

            System.out.println("\nChoose an action:");
            System.out.println("1. Play with egg (+10 happiness)");
            System.out.println("2. Move around (+5 happiness)");
            System.out.println("3. Rest (+15 happiness)");
            System.out.println("4. Do nothing (+2 happiness)");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    egg.increaseHappiness(10);
                    break;
                case 2:
                    egg.increaseHappiness(5);
                    break;
                case 3:
                    egg.increaseHappiness(15);
                    break;
                case 4:
                    egg.increaseHappiness(2);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            egg.displayHappinessBar();
        }

        // HATCHING
        pet = egg.hatch();
        System.out.println("You got: " + pet);

        // NAME YOUR PET
        System.out.print("Name your " + pet + ": ");
        String name = scanner.nextLine();

        System.out.println("\n🎉 Your " + pet + " named " + name + " is ready!");

        // OPTIONAL: simple post-hatch loop
        boolean running = true;

        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Play");
            System.out.println("2. Rest");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(name + " is playing happily!");
                    break;
                case 2:
                    System.out.println(name + " is resting...");
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }
}
