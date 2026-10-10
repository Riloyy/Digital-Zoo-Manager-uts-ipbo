import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * ZooFrame.java
 * The main window (View) for the Digital Zoo Manager application.
 *
 * - Input form: Name (JTextField), Age (JTextField),
 *   animal type (JComboBox), Fur Color (JTextField) / Can Fly (JCheckBox).
 * - Action button: "Add Animal".
 * - Display area: non-editable JTextArea acting as a log.
 * - Data storage: ArrayList&lt;Animal&gt; demonstrating polymorphism
 *   (holds both Mammal and Bird objects).
 */
public class ZooFrame extends JFrame {

    private final ArrayList<Animal> zoo = new ArrayList<>();

    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> typeComboBox;
    private JTextField furColorField;
    private JCheckBox canFlyCheckBox;
    private JPanel specificCardPanel;
    private CardLayout specificCardLayout;
    private JButton addButton;
    private JTextArea logArea;

    private static final String CARD_MAMMAL = "MAMMAL";
    private static final String CARD_BIRD = "BIRD";

    public ZooFrame() {
        super("Digital Zoo Manager");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 480);
        setLocationRelativeTo(null); // center on screen
        setLayout(new BorderLayout(10, 10));

        // ===== Input form (NORTH) =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add a New Animal"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Name:"), gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        formPanel.add(nameField, gbc);

        // Row 1: Age
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Age:"), gbc);

        ageField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        formPanel.add(ageField, gbc);

        // Row 2: Animal type
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Animal Type:"), gbc);

        typeComboBox = new JComboBox<>(new String[]{"Mammal", "Bird"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        formPanel.add(typeComboBox, gbc);

        // Row 3: Specific attribute (CardLayout switches Fur Color <-> Can Fly)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Detail:"), gbc);

        specificCardLayout = new CardLayout();
        specificCardPanel = new JPanel(specificCardLayout);

        JPanel mammalPanel = new JPanel(new BorderLayout(5, 5));
        mammalPanel.add(new JLabel("Fur Color:"), BorderLayout.WEST);
        furColorField = new JTextField(15);
        mammalPanel.add(furColorField, BorderLayout.CENTER);

        JPanel birdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        canFlyCheckBox = new JCheckBox("Can Fly?", true);
        birdPanel.add(canFlyCheckBox);

        specificCardPanel.add(mammalPanel, CARD_MAMMAL);
        specificCardPanel.add(birdPanel, CARD_BIRD);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        formPanel.add(specificCardPanel, gbc);

        typeComboBox.addActionListener(e -> updateSpecificPanel());

        add(formPanel, BorderLayout.NORTH);

        // ===== Action button (CENTER top) =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add Animal");
        addButton.addActionListener(e -> onAddAnimal());
        buttonPanel.add(addButton);
        // Wrap button + log so layout stays neat
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(buttonPanel, BorderLayout.NORTH);

        // ===== Display area / log (CENTER) =====
        logArea = new JTextArea(12, 40);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Zoo Log"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(centerPanel, BorderLayout.CENTER);

        updateSpecificPanel();
    }

    private void updateSpecificPanel() {
        String selected = (String) typeComboBox.getSelectedItem();
        if ("Bird".equals(selected)) {
            specificCardLayout.show(specificCardPanel, CARD_BIRD);
        } else {
            specificCardLayout.show(specificCardPanel, CARD_MAMMAL);
        }
    }

    private void onAddAnimal() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String type = (String) typeComboBox.getSelectedItem();

        // --- Validation ---
        if (name.isEmpty()) {
            showError("Please enter the animal's name.");
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age < 0) {
                showError("Age must be 0 or greater.");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Age must be a valid whole number.");
            return;
        }

        Animal animal;
        String typeLabel;
        if ("Bird".equals(type)) {
            boolean canFly = canFlyCheckBox.isSelected();
            animal = new Bird(name, age, canFly);
            typeLabel = "Bird";
        } else {
            String furColor = furColorField.getText().trim();
            if (furColor.isEmpty()) {
                showError("Please enter the fur color for the mammal.");
                return;
            }
            animal = new Mammal(name, age, furColor);
            typeLabel = "Mammal";
        }

        // --- Polymorphism: both Mammal and Bird stored as Animal ---
        zoo.add(animal);

        // --- Update log via polymorphic getInfo() and makeSound() ---
        logArea.append("Added a new " + typeLabel + "! Info: "
                + animal.getInfo() + ". Sound: " + animal.makeSound()
                + " (Total animals: " + zoo.size() + ")\n");

        clearForm();
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        furColorField.setText("");
        canFlyCheckBox.setSelected(true);
        nameField.requestFocusInWindow();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message,
                "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    /** For testing/programmatic access: returns number of animals stored. */
    public int getZooSize() {
        return zoo.size();
    }
}
