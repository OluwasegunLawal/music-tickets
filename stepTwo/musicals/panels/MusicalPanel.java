package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import musicals.models.Musical;
import musicals.models.ShowTime;
import musicals.utils.Helpers;

import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
 * Panel responsible for displaying musical selection interface.
 * This panel shows available musicals and allows users to select show times and dates.
 */
public class MusicalPanel extends JPanel {
    // Main application window reference
    private MainFrame mainFrame;
    // Manages the ordering process and state
    private OrderManager orderManager;
    // List component to display available musicals
    private JList<Musical> musicalList;
    // Dropdown to select performance dates
    private JComboBox<LocalDate> dateSelector;
    // List component to display available show times
    private JList<ShowTime> showTimeList;

    /**
     * Constructs a new MusicalPanel with references to main application components.
     * @param mainFrame The main application window
     * @param orderManager The order management system
     */
    public MusicalPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    /**
     * Initializes and arranges all UI components of the panel.
     * Creates a header and a grid of musical selection boxes.
     */
    private void initializeComponents() {
        // Create and add the header section
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), 
            mainFrame.getMainContainer(), "Welcome to London Musicals");
        add(headerContainer, BorderLayout.NORTH);

        // Create panel to hold musical selection boxes
        JPanel countPanel = new JPanel();
        // Create 8 musical selection boxes with labels
        for (int i = 1; i <= 8; i++) {
            // Container for each musical box and its label
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BorderLayout());
            
            // Create numbered box with border
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setPreferredSize(new Dimension(200, 200));
            box.add(new JLabel(String.valueOf(i)));
            
            // Add the box and its label to the container
            containerPanel.add(box, BorderLayout.CENTER);
            containerPanel.add(new JLabel("Musical " + i, JLabel.LEFT), BorderLayout.SOUTH);
            
            countPanel.add(containerPanel);
        }
        add(countPanel, BorderLayout.CENTER);
    }
} 