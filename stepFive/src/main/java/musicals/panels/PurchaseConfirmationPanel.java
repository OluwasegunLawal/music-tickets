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

public class PurchaseConfirmationPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;
    private JLabel musicalLabel;
    private JLabel dateLabel;
    private JLabel seatsLabel;
    private JLabel typeLabel;
    private JLabel priceLabel;

    public PurchaseConfirmationPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        mainFrame.setTitle("Purchase Confirmation");
        
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), 
            mainFrame.getMainContainer(), "Purchase Confirmation", true, "MUSICAL_DETAILS");
        
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(5, 1, 1, 1));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        musicalLabel = new JLabel();
        dateLabel = new JLabel();
        seatsLabel = new JLabel();
        typeLabel = new JLabel();
        priceLabel = new JLabel();
        
        Font detailsFont = new Font("Arial", Font.PLAIN, 16);
        musicalLabel.setFont(detailsFont);
        dateLabel.setFont(detailsFont);
        seatsLabel.setFont(detailsFont);
        typeLabel.setFont(detailsFont);
        priceLabel.setFont(detailsFont);
        
        detailsPanel.add(musicalLabel);
        detailsPanel.add(dateLabel);
        detailsPanel.add(seatsLabel);
        detailsPanel.add(typeLabel);
        detailsPanel.add(priceLabel);
        
        contentContainer.add(detailsPanel, BorderLayout.CENTER);
        
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JButton txtButton = new JButton("Download as TXT");
        
        txtButton.addActionListener(e -> downloadAsText());
        buttonContainer.add(txtButton);
        
        add(buttonContainer, BorderLayout.SOUTH);
        add(contentContainer, BorderLayout.CENTER);

        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.add(headerContainer, BorderLayout.NORTH);

        add(northContainer, BorderLayout.NORTH);
    }

    public void updateConfirmationDetails() {
        if (orderManager.getSelectedMusical() != null) {
            musicalLabel.setText("Musical: " + orderManager.getSelectedMusical().getTitle());
            dateLabel.setText("Date & Time: " + orderManager.getBookingDate() + " at " + orderManager.getBookingTime());
            seatsLabel.setText("Number of Seats: " + orderManager.getBookingSeats());
            typeLabel.setText("Ticket Type: " + orderManager.getBookingTicketType());
            priceLabel.setText(String.format("Total Price: £%.2f", orderManager.getBookingTotalPrice()));
        }
    }

    private void downloadAsText() {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            
            String filename = "booking_" + timestamp + ".txt";
            
            StringBuilder content = new StringBuilder();
            content.append("=== Musical Ticket Booking Details ===\n\n");
            content.append("Booking Reference: ").append(timestamp).append("\n");
            content.append("Date: ").append(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n\n");
            
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
            
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(content.toString());
            }
            
            JOptionPane.showMessageDialog(this,
                "Booking details have been saved to " + filename,
                "Download Successful",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error saving booking details: " + ex.getMessage(),
                "Download Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 