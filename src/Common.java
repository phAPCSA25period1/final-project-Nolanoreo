/**
 * Represents a Beserker character with high damage, high health,
 * and a rage-driven behavior. The Beserker can attack, move, rest,
 * or do nothing—each action affecting health and happiness differently.
 * If happiness drops below zero, the character dies.
 */
public class Common implements Character {

    /** Time delay in milliseconds to simulate action pauses. */
    private int time = 1500;

    /** Name of the Beserker. */
    private String name;

    /** The damage dealt by the Beserker when attacking. */
    private int damage;

    /** The current health of the Beserker. */
    private int health;

    /** The current happiness (rage stability) of the Beserker. */
    private int happiness;

    /**
     * Constructs a new Beserker with a specified name and default attributes.
     *
     * @param name the name of the Beserker
     */
    public Beserker(String name) {
        this.name = name;
        this.damage = 90;
        this.health = 200;
        this.happiness = 100;
    }

    /**
     * Displays information about the Beserker, including name, damage,
     * health, and happiness.
     */
    @Override
    public void displayInfo() {
        System.out.println("\n--- Beserker Info ---");
        System.out.println("Name: " + name);
        System.out.println("Damage: " + damage);
        delay();
        System.out.println("Health: " + health);
        System.out.println("Happiness: " + happiness);
        delay();
    }

    /**
     * Performs a powerful attack. The Beserker deals significant damage,
     * but loses health due to the reckless nature of the attack.
     */
    @Override
    public void attack() {
        System.out.println(name + " unleashes a furious attack, dealing " + damage + " damage!");
        delay();
        System.out.println(name + " loses 40 health due to the reckless attack.");
        this.health -= 40;
        this.happiness += 10;
        checkHappiness();
    }

    /**
     * Moves the Beserker forward aggressively. Movement causes self-inflicted harm
     * but increases happiness due to adrenaline.
     */
    @Override
    public void move() {
        System.out.println(name + " charges forward with unstoppable rage!");
        delay();
        System.out.println(name + " stumbles slightly, losing 30 health.");
        this.health -= 30;
        this.happiness += 15;
        checkHappiness();
    }

    /**
     * Allows the Beserker to rest, regaining health through meditation.
     */
    @Override
    public void rest() {
        System.out.println(name + " is meditating to regain health.");
        delay();
        System.out.println(name + " regains 35 health.");
        this.health += 35;
        checkHappiness();
    }

    /**
     * Performs no action. Happiness decreases due to growing restlessness.
     */
    @Override
    public void doNothing() {
        System.out.println(name + " is standing still, trying to calm the rage.");
        delay();
        System.out.println(name + " feels restless and loses happiness.");
        this.happiness -= 10;
        checkHappiness();
    }

    /**
     * Retrieves the current health of the Beserker.
     *
     * @return the Beserker's health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Retrieves the Beserker's name.
     *
     * @return the Beserker's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Checks the happiness level of the Beserker. If happiness drops below zero,
     * the Beserker dies and health becomes zero.
     */
    private void checkHappiness() {
        if (this.happiness < 0) {
            this.health = 0;
            System.out.println(name + " has succumbed to despair and has died!");
        }
    }

    /**
     * Introduces a timed delay to simulate real-time action pacing.
     * Prints an error if the delay is interrupted.
     */
    private void delay() {
        try {
            Thread.sleep(time); // Delay for the specified time (1500 ms)
        } catch (InterruptedException e) {
            System.err.println("Delay interrupted: " + e.getMessage());
        }
    }
}
