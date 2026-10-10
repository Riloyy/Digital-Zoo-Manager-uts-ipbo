/**
 * Bird.java
 * Subclass of Animal representing a bird.
 */
public class Bird extends Animal {
    private boolean canFly;

    /**
     * Constructor: calls the parent constructor and initializes canFly.
     */
    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    // Getter and Setter for canFly
    public boolean isCanFly() {
        return canFly;
    }

    public boolean getCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public String makeSound() {
        return "The bird chirps.";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Can Fly: " + (canFly ? "Yes" : "No");
    }
}
