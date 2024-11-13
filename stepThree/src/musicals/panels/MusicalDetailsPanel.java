package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.models.Musical;
import musicals.utils.Helpers;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;

/**
 * Panel for displaying and managing musical event details and booking options.
 */
public class MusicalDetailsPanel extends JPanel {

    private MainFrame mainFrame;
    private OrderManager orderManager;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JLabel durationLabel;
    private JLabel imageLabel;

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
        // Create and add the header with navigation and back button
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), 
            mainFrame.getMainContainer(), "Musical Details", true);
        add(headerContainer, BorderLayout.NORTH);

        // Create main container with two-column layout
        JPanel mainContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        mainContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left column setup - Musical image and basic info
        JPanel leftColumn = new JPanel(new BorderLayout(0, 10));
        leftColumn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Musical Information"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Create a panel to hold the image label
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(300, 400));
        
        // Initialize the image label
        imageLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getIcon() == null) {
                    return;
                }
                
                Image img = ((ImageIcon) getIcon()).getImage();
                BufferedImage image = new BufferedImage(
                    img.getWidth(null), 
                    img.getHeight(null), 
                    BufferedImage.TYPE_INT_ARGB
                );
                Graphics2D bGr = image.createGraphics();
                bGr.drawImage(img, 0, 0, null);
                bGr.dispose();
                
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                                   RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                
                // Calculate dimensions to cover the entire area
                double scale = Math.max(
                    (double) getWidth() / image.getWidth(),
                    (double) getHeight() / image.getHeight()
                );
                
                int scaledWidth = (int) (image.getWidth() * scale);
                int scaledHeight = (int) (image.getHeight() * scale);
                
                // Center the image
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;
                
                g2d.drawImage(image, x, y, scaledWidth, scaledHeight, this);
            }
        };
        imageLabel.setPreferredSize(new Dimension(300, 400));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        leftColumn.add(imagePanel, BorderLayout.CENTER);
        
        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        descriptionLabel = new JLabel();
        priceLabel = new JLabel();
        durationLabel = new JLabel();
        
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(descriptionLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(priceLabel);
        infoPanel.add(durationLabel);
        
        leftColumn.add(infoPanel, BorderLayout.SOUTH);
        
        // Right column setup - Booking options
        JPanel rightColumn = new JPanel(new BorderLayout(0, 10));
        rightColumn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Booking Options"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Booking options panel
        JPanel bookingPanel = new JPanel(new GridLayout(4, 2, 10, 20));
        
        bookingPanel.add(new JLabel("Select Date:"));
        bookingPanel.add(new JComboBox<>(new String[]{"2024-03-20", "2024-03-21", "2024-03-22"}));
        
        bookingPanel.add(new JLabel("Select Time:"));
        bookingPanel.add(new JComboBox<>(new String[]{"19:00", "20:00", "21:00"}));
        
        bookingPanel.add(new JLabel("Number of Seats:"));
        JComboBox<Integer> seatsCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        bookingPanel.add(seatsCombo);
        
        bookingPanel.add(new JLabel("Total Price:"));
        JLabel totalPriceLabel = new JLabel("£0.00");
        bookingPanel.add(totalPriceLabel);
        
        // Update total price when seat count changes
        seatsCombo.addActionListener(e -> {
            Musical musical = orderManager.getSelectedMusical();
            if (musical != null) {
                int seats = (Integer) seatsCombo.getSelectedItem();
                double total = musical.getBasePrice() * seats;
                totalPriceLabel.setText(String.format("£%.2f", total));
            }
        });
        
        rightColumn.add(bookingPanel, BorderLayout.CENTER);
        
        // Create and add the bottom button panel
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Book Tickets");
        submitButton.addActionListener(e -> {
            // TODO: Implement booking logic
            JOptionPane.showMessageDialog(this, "Booking functionality coming soon!");
        });
        buttonPanel.add(submitButton);
        rightColumn.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add both columns to main container
        mainContainer.add(leftColumn);
        mainContainer.add(rightColumn);
        
        // Add main container to panel
        add(mainContainer, BorderLayout.CENTER);
    }

    /**
     * Updates the panel with the selected musical's information
     */
    public void updateMusicalDetails() {
        Musical musical = orderManager.getSelectedMusical();
        if (musical != null) {
            titleLabel.setText(musical.getTitle());
            descriptionLabel.setText(musical.getDescription());
            priceLabel.setText(String.format("Base Price: £%.2f", musical.getBasePrice()));
            durationLabel.setText(String.format("Duration: %d minutes", musical.getDurationMinutes()));
            
            // Load and display the image
            try {
                URL url = new URL(musical.getImagePath());
                BufferedImage originalImage = ImageIO.read(url);
                imageLabel.setIcon(new ImageIcon(originalImage));
                imageLabel.revalidate();
                imageLabel.repaint();
            } catch (Exception e) {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not available");
                e.printStackTrace(); // For debugging
            }
        }
    }
}
