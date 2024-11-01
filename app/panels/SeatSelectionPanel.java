package app.panels;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import app.MainFrame;
import app.OrderManager;
import app.models.TicketType;

public class SeatSelectionPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;
    private JPanel seatGrid;
    private JComboBox<TicketType> ticketTypeSelector;

    public SeatSelectionPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        // Initialize seat grid and ticket type selector
        // When seats are selected, call mainFrame.switchToPanel("CART")
    }
} 