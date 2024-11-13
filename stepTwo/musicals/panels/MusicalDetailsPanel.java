package musicals.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.utils.Helpers;

/**
 * Panel for displaying and managing musical event details and booking options.
 */
public class MusicalDetailsPanel extends JPanel {

    private MainFrame mainFrame;
    private OrderManager orderManager;

    /**
     * Constructor initializes the panel with main frame and order manager references.
     */
    public MusicalDetailsPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;

        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        mainFrame.setTitle("Musical Details");

        // Create and add the header with navigation
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "Musical Details");
        add(headerContainer, BorderLayout.NORTH);

        // Create main container with two-column layout
        JPanel mainContainer = new JPanel(new GridLayout(1, 2, 10, 0));
        
        // Left column setup - reserved for future content
        JPanel leftColumn = new JPanel(new BorderLayout());
        leftColumn.setBorder(BorderFactory.createTitledBorder("Left Column"));
        
        // Right column setup - contains event details and booking options
        JPanel rightColumn = new JPanel(new GridLayout(2, 1, 0, 10));
        rightColumn.setBorder(BorderFactory.createTitledBorder("Right Column"));
        
        // Box 1: Event details section
        JPanel box1 = new JPanel(new BorderLayout());
        box1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel detailsLabel = new JLabel("Details about the event", JLabel.CENTER);
        box1.add(detailsLabel, BorderLayout.CENTER);
        
        // Box 2: Booking options section with date, time, and seat selection
        JPanel box2 = new JPanel(new GridLayout(3, 2, 10, 10));
        box2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Add booking option components
        box2.add(new JLabel("Date:"));
        box2.add(new JComboBox<>(new String[]{"2024-03-20", "2024-03-21", "2024-03-22"}));
        box2.add(new JLabel("Time:"));
        box2.add(new JComboBox<>(new String[]{"19:00", "20:00", "21:00"}));
        box2.add(new JLabel("Seats:"));
        box2.add(new JComboBox<>(new String[]{"1", "2", "3", "4"}));
        
        // Assemble right column components
        rightColumn.add(box1);
        rightColumn.add(box2);
        
        // Add both columns to main container
        mainContainer.add(leftColumn);
        mainContainer.add(rightColumn);
        
        // Create and add the bottom button panel
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Book Ticket");
        buttonPanel.add(submitButton);
        
        // Add main components to the panel using BorderLayout
        add(mainContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
