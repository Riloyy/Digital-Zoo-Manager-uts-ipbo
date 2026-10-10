import javax.swing.SwingUtilities;

/**
 * Main.java
 * Application entry point for the Digital Zoo Manager.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZooFrame frame = new ZooFrame();
            frame.setVisible(true);
        });
    }
}
