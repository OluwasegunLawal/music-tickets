package app.utils;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class Helpers {

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

    public static void switchToPanel(CardLayout cardLayout, JPanel mainContainer, String panelName) {
        cardLayout.show(mainContainer, panelName);
    }
}
