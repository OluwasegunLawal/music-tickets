package app.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.MainFrame;
import app.OrderManager;
import app.utils.Helpers;

public class MusicalDetailsPanel extends JPanel {

    private MainFrame mainFrame;
    private OrderManager orderManager;

    public MusicalDetailsPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;

        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        mainFrame.setTitle("Musical Details");

        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "Musical Details");
        add(headerContainer, BorderLayout.NORTH);

        // Main container with GridLayout(1, 2) for two columns
        JPanel mainContainer = new JPanel(new GridLayout(1, 2, 10, 0));
        
        // Left column - single box
        JPanel leftColumn = new JPanel(new BorderLayout());
        leftColumn.setBorder(BorderFactory.createTitledBorder("Left Column"));
        
        // Right column - three boxes
        JPanel rightColumn = new JPanel(new GridLayout(2, 1, 0, 10));
        rightColumn.setBorder(BorderFactory.createTitledBorder("Right Column"));
        
        // Add text to box1
        JPanel box1 = new JPanel(new BorderLayout());
        box1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel detailsLabel = new JLabel("Details about the event", JLabel.CENTER);
        box1.add(detailsLabel, BorderLayout.CENTER);
        
        // Add combo boxes to box2
        JPanel box2 = new JPanel(new GridLayout(3, 2, 10, 10));
        box2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        box2.add(new JLabel("Date:"));
        box2.add(new JComboBox<>(new String[]{"2024-03-20", "2024-03-21", "2024-03-22"}));
        box2.add(new JLabel("Time:"));
        box2.add(new JComboBox<>(new String[]{"19:00", "20:00", "21:00"}));
        box2.add(new JLabel("Seats:"));
        box2.add(new JComboBox<>(new String[]{"1", "2", "3", "4"}));
        
        rightColumn.add(box1);
        rightColumn.add(box2);
        
        // Add columns to main container
        mainContainer.add(leftColumn);
        mainContainer.add(rightColumn);
        
        // Create button panel for bottom
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Book Ticket");
        buttonPanel.add(submitButton);
        
        // Add main container to the center and button to the bottom
        add(mainContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
