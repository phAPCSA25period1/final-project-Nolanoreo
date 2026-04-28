/**
 * Represents a pet with a name, type, rarity, and happiness level.
 * The pet can be interacted with through simple actions.
 */
public class Pet {

    private String name;
    private String type;
    private String rarity;
    private int happiness;

    private final int MAX_HAPPINESS = 100;

    /**
     * Creates a new pet with the specified name, type, and rarity.
     * The pet begins with a default happiness of 50.
     *
     * @param name   the pet's name
     * @param type   the pet's type
     * @param rarity the pet's rarity tier
     */
    public Pet(String name, String type, String rarity) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.happiness = 50;
    }

    // --- Actions ---
    /**
     * Increases happiness by 10 for a fetch action.
     */
    public void fetch() {
        happiness += 10;
    }

    /**
     * Increases happiness by 5 for a walk action.
     */
    public void walk() {
        happiness += 5;
    }

    /**
     * Increases happiness by 20 for a rest action.
     */
    public void rest() {
        happiness += 20;
    }

    /**
     * Increases happiness by 15 for a feed action.
     */
    public void feed() {
        happiness += 15;
    }

    public void capHappiness() {
        if (happiness > MAX_HAPPINESS) {
            happiness = MAX_HAPPINESS;
        }
    }

    public boolean isMaxHappiness() {
        return happiness >= MAX_HAPPINESS;
    }

    public void displayBar() {
        int bars = happiness / 10;

        System.out.print("Happiness: [");
        for (int i = 0; i < bars; i++)
            System.out.print("#");
        for (int i = bars; i < 10; i++)
            System.out.print("-");
        System.out.println("] " + happiness + "/100");
    }

    public String toString() {
        return name + " (" + rarity + " " + type + ")";
    }

    public String getName() {
        return name;
    }
}
