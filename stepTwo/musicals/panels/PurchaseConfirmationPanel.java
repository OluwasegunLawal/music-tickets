package musicals.panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.utils.Helpers;

/**
 * Panel that displays the purchase confirmation details and download options
 * for a musical ticket order.
 */
public class PurchaseConfirmationPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;

    /**
     * Constructs a new PurchaseConfirmationPanel with references to main application components
     * @param mainFrame The main application frame
     * @param orderManager The order management system
     */
    public PurchaseConfirmationPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    /**
     * Initializes and arranges all UI components of the panel
     */
    private void initializeComponents() {
        // Set the window title
        mainFrame.setTitle("Purchase Confirmation");
        
        // Create the header with navigation functionality
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), 
            mainFrame.getMainContainer(), "Purchase Confirmation");
        
        // Add back button in a sub-header panel
        JButton backButton = new JButton("Back");
        JPanel subHeaderContainer = new JPanel(new BorderLayout());
        subHeaderContainer.add(backButton, BorderLayout.WEST);

        // Create main content panel with padding
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create grid panel for purchase details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(5, 1, 2, 2));
        
        // Add label placeholders for order information
        // TODO: These should be populated with actual order details
        detailsPanel.add(new JLabel("Musical:"));
        detailsPanel.add(new JLabel("Date:"));
        detailsPanel.add(new JLabel("Seats:"));
        detailsPanel.add(new JLabel("Ticket Type:"));
        detailsPanel.add(new JLabel("Price:"));
        
        contentContainer.add(detailsPanel, BorderLayout.CENTER);
        
        // Create container for download buttons with centered layout and spacing
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JButton txtButton = new JButton("Download as TXT");
        JButton pdfButton = new JButton("Download as PDF");
        
        buttonContainer.add(txtButton);
        buttonContainer.add(pdfButton);
        
        // Add button container to the bottom
        add(buttonContainer, BorderLayout.SOUTH);
        
        // Add content container to main panel
        add(contentContainer, BorderLayout.CENTER);

        // Create main north container that will hold both header and subheader
        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.add(headerContainer, BorderLayout.NORTH);
        northContainer.add(subHeaderContainer, BorderLayout.SOUTH);
        
        // Add the combined north container instead of adding them separately
        add(northContainer, BorderLayout.NORTH);
    }
} 