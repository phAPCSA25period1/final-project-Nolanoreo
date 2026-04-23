public class Pet {

    private String name;
    private String type;
    private String rarity;
    private int happiness;

    private final int MAX_HAPPINESS = 100;

    public Pet(String name, String type, String rarity) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.happiness = 50;
    }

    // --- Actions ---
    public void fetch() { happiness += 10; }
    public void walk() { happiness += 5; }
    public void rest() { happiness += 20; }
    public void feed() { happiness += 15; }

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
        for (int i = 0; i < bars; i++) System.out.print("#");
        for (int i = bars; i < 10; i++) System.out.print("-");
        System.out.println("] " + happiness + "/100");
    }

    public String toString() {
        return name + " (" + rarity + " " + type + ")";
    }

    public String getName() { return name; }
}
