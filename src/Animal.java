/**
 * Animal.java
 * The superclass (base class) for all animals in the Digital Zoo Manager.
 */
public class Animal {
    private String name;
    private int age;

    /**
     * Constructor to initialize name and age.
     */
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a generic sound string.
     */
    public String makeSound() {
        return "The animal makes a sound.";
    }

    /**
     * Returns a string with the animal's name and age.
     */
    public String getInfo() {
        return "Name: " + name + ", Age: " + age;
    }
}
