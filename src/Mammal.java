/**
 * Mammal.java
 * Subclass of Animal representing a mammal.
 */
public class Mammal extends Animal {
    private String furColor;

    /**
     * Constructor: calls the parent constructor and initializes furColor.
     */
    public Mammal(String name, int age, String furColor) {
        super(name, age);
        this.furColor = furColor;
    }

    // Getter and Setter for furColor
    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    @Override
    public String makeSound() {
        return "The mammal makes a sound.";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Fur Color: " + furColor;
    }
}
