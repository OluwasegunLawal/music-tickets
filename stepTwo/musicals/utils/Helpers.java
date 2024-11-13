package musicals.utils;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

/**
 * Utility class providing helper methods for UI components and navigation
 */
public class Helpers {

    /**
     * Creates and configures a header panel with a title and navigation button
     * 
     * @param cardLayout The CardLayout managing the application's panels
     * @param mainContainer The main container holding all panels
     * @param title The title text to display in the header
     * @return A configured JPanel containing the header elements
     */
    public static JPanel getHeaderContainer(CardLayout cardLayout, JPanel mainContainer, String title) {
        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
        headerContainer.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Configure welcome label
        JLabel welcomeLabel = new JLabel(title);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Configure start button
        JButton startButton = new JButton("Musical List");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(e -> switchToPanel(cardLayout, mainContainer, "MUSICALS"));

        // Add glue for spacing
        headerContainer.add(welcomeLabel);
        headerContainer.add(Box.createHorizontalGlue()); // Creates space between components
        headerContainer.add(startButton);

        return headerContainer;
    }

    /**
     * Utility method to switch between panels in a CardLayout
     * 
     * @param cardLayout The CardLayout managing the panels
     * @param mainContainer The container holding the panels
     * @param panelName The name identifier of the panel to switch to
     */
    public static void switchToPanel(CardLayout cardLayout, JPanel mainContainer, String panelName) {
        cardLayout.show(mainContainer, panelName);
    }
}
