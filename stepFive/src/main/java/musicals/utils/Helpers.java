package musicals.utils;

import javax.swing.*;
import java.awt.*;

public class Helpers {

    public static JPanel getHeaderContainer(CardLayout cardLayout, JPanel mainContainer, String title, boolean showBackButton, String backTo) {
        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
        headerContainer.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        if (showBackButton) {
            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Arial", Font.PLAIN, 16));
            backButton.addActionListener(e -> switchToPanel(cardLayout, mainContainer, backTo));
            headerContainer.add(backButton);
            headerContainer.add(Box.createRigidArea(new Dimension(20, 0))); // Add spacing after back button
        }

        JLabel welcomeLabel = new JLabel(title);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startButton = new JButton("Musical List");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(e -> switchToPanel(cardLayout, mainContainer, "MUSICALS"));

        headerContainer.add(welcomeLabel);
        headerContainer.add(Box.createHorizontalGlue());
        headerContainer.add(startButton);

        return headerContainer;
    }

    public static void switchToPanel(CardLayout cardLayout, JPanel mainContainer, String panelName) {
        cardLayout.show(mainContainer, panelName);
    }
}
