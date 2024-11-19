package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.models.TicketType;

import javax.swing.*;
import java.awt.*;

/**
 * Panel responsible for displaying the seat selection interface.
 * Allows users to select seats and ticket types for a musical performance.
 */
public class SeatSelectionPanel extends JPanel {
    // Reference to main application window for navigation
    private MainFrame mainFrame;
    
    // Manages the current order state and operations
    private OrderManager orderManager;
    
    // Grid panel to display available/unavailable seats
    private JPanel seatGrid;
    
    // Dropdown to select ticket type (e.g., Adult, Child, Senior)
    private JComboBox<TicketType> ticketTypeSelector;

    /**
     * Constructs a new seat selection panel.
     * @param mainFrame The main application window
     * @param orderManager The order management system
     */
    public SeatSelectionPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    /**
     * Initializes and sets up the panel components.
     * Creates the seat grid and ticket type selector.
     * When seats are selected, user will be redirected to the cart panel.
     */
    private void initializeComponents() {
        // Initialize seat grid and ticket type selector
        // When seats are selected, call mainFrame.switchToPanel("CART")
    }
} 