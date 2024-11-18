package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.utils.Helpers;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Panel that displays the purchase confirmation details and download options
 * for a musical ticket order.
 */
public class PurchaseConfirmationPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;
    private JLabel musicalLabel;
    private JLabel dateLabel;
    private JLabel seatsLabel;
    private JLabel typeLabel;
    private JLabel priceLabel;

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
        
        // Create the header with back button navigation to MUSICAL_DETAILS screen
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), 
            mainFrame.getMainContainer(), "Purchase Confirmation", true, "MUSICAL_DETAILS");
        
        // Create main content panel with padding for better visual spacing
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create grid panel for purchase details with 5 rows and equal spacing
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(5, 1, 1, 1));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Initialize labels that will be populated later with booking details
        musicalLabel = new JLabel();
        dateLabel = new JLabel();
        seatsLabel = new JLabel();
        typeLabel = new JLabel();
        priceLabel = new JLabel();
        
        // Set font for better readability
        Font detailsFont = new Font("Arial", Font.PLAIN, 16);
        musicalLabel.setFont(detailsFont);
        dateLabel.setFont(detailsFont);
        seatsLabel.setFont(detailsFont);
        typeLabel.setFont(detailsFont);
        priceLabel.setFont(detailsFont);
        
        // Add labels to details panel
        detailsPanel.add(musicalLabel);
        detailsPanel.add(dateLabel);
        detailsPanel.add(seatsLabel);
        detailsPanel.add(typeLabel);
        detailsPanel.add(priceLabel);
        
        contentContainer.add(detailsPanel, BorderLayout.CENTER);
        
        // Create container for download buttons with centered layout and spacing
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JButton txtButton = new JButton("Download as TXT");
        
        txtButton.addActionListener(e -> downloadAsText());
        buttonContainer.add(txtButton);
        
        // Add button container to the bottom
        add(buttonContainer, BorderLayout.SOUTH);
        
        // Add content container to main panel
        add(contentContainer, BorderLayout.CENTER);

        // Create main north container that will hold both header and subheader
        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.add(headerContainer, BorderLayout.NORTH);

        // Add the combined north container instead of adding them separately
        add(northContainer, BorderLayout.NORTH);
    }

    /**
     * Updates the confirmation details with the current booking information
     */
    public void updateConfirmationDetails() {
        if (orderManager.getSelectedMusical() != null) {
            musicalLabel.setText("Musical: " + orderManager.getSelectedMusical().getTitle());
            dateLabel.setText("Date & Time: " + orderManager.getBookingDate() + " at " + orderManager.getBookingTime());
            seatsLabel.setText("Number of Seats: " + orderManager.getBookingSeats());
            typeLabel.setText("Ticket Type: " + orderManager.getBookingTicketType());
            priceLabel.setText(String.format("Total Price: £%.2f", orderManager.getBookingTotalPrice()));
        }
    }

    /**
     * Downloads the booking details as a text file
     */
    private void downloadAsText() {
        try {
            // Generate unique timestamp for both filename and booking reference
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            
            // Create filename in format: booking_YYYYMMDD_HHMMSS.txt
            String filename = "booking_" + timestamp + ".txt";
            
            // Build the content with booking details and important information
            StringBuilder content = new StringBuilder();
            content.append("=== Musical Ticket Booking Details ===\n\n");
            content.append("Booking Reference: ").append(timestamp).append("\n");
            content.append("Date: ").append(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n\n");
            
            // Add booking details
            content.append("Musical: ").append(orderManager.getSelectedMusical().getTitle()).append("\n");
            content.append("Performance Date: ").append(orderManager.getBookingDate()).append("\n");
            content.append("Performance Time: ").append(orderManager.getBookingTime()).append("\n");
            content.append("Ticket Type: ").append(orderManager.getBookingTicketType()).append("\n");
            content.append("Number of Seats: ").append(orderManager.getBookingSeats()).append("\n");
            content.append("Total Price: £").append(String.format("%.2f", orderManager.getBookingTotalPrice())).append("\n\n");
            
            content.append("=== Important Information ===\n");
            content.append("Please arrive at least 30 minutes before the show.\n");
            content.append("Present this booking confirmation at the ticket counter.\n");
            content.append("No refunds are available for purchased tickets.\n");
            
            // Write content to file and close automatically using try-with-resources
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(content.toString());
            }
            
            // Display success message with filename for user reference
            JOptionPane.showMessageDialog(this,
                "Booking details have been saved to " + filename,
                "Download Successful",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException ex) {
            // Display error dialog if file writing fails
            JOptionPane.showMessageDialog(this,
                "Error saving booking details: " + ex.getMessage(),
                "Download Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 