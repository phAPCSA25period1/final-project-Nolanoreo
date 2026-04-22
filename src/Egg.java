import java.util.Random;

public class Egg {

    private int happiness = 0;
    private static final int MAX_HAPPINESS = 100;
    private static final String[] PETS = {
            "Dog", "Cat", "Buffalo", "Otter", "Robin",
            "Fennec Fox", "Puma", "Snow Cat", "Chocolate Labrador", "Dingo",
            "Beaver", "Bunny", "Monkey", "Rabbit", "Snow Puma",
            "Red Panda", "Shiba Inu", "Frog", "Penguin", "Koala",
            "Dragon", "Unicorn", "Griffin", "Kangaroo", "Turtle"
    };
    private static final Random rand = new Random();

    public boolean isReadyToHatch() {
        return happiness >= MAX_HAPPINESS;
    }

    public void increaseHappiness(int amount) {
        happiness += amount;
        if (happiness > MAX_HAPPINESS) {
            happiness = MAX_HAPPINESS;
        }
    }

    public void displayHappinessBar() {
        int bars = happiness / 10;
        System.out.print("Happiness: [");
        for (int i = 0; i < bars; i++) {
            System.out.print("#");
        }
        for (int i = bars; i < 10; i++) {
            System.out.print("-");
        }
        System.out.println("] " + happiness + "/100");
    }

    public String hatch() {
        int index = rand.nextInt(PETS.length);
        return PETS[index];
    }
}
