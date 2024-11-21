package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.models.Musical;
import musicals.models.TicketType;
import musicals.utils.Helpers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class MusicalDetailsPanel extends JPanel {

    private MainFrame mainFrame;
    private OrderManager orderManager;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JLabel durationLabel;
    private JLabel imageLabel;
    private JComboBox<String> dateCombo;
    private JComboBox<String> timeCombo;
    private JComboBox<TicketType> ticketTypeCombo;
    private JComboBox<Integer> seatsCombo;
    private JLabel totalPriceLabel;

    public MusicalDetailsPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
        headerContainer.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            resetForm();  
            Helpers.switchToPanel(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "MUSICALS");
        });
        headerContainer.add(backButton);
        headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));

        JLabel welcomeLabel = new JLabel("Musical Details");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton startButton = new JButton("Musical List");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(e -> {
            resetForm(); 
            Helpers.switchToPanel(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "MUSICALS");
        });

        headerContainer.add(welcomeLabel);
        headerContainer.add(Box.createHorizontalGlue());
        headerContainer.add(startButton);

        add(headerContainer, BorderLayout.NORTH);

        JPanel mainContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        mainContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel leftColumn = new JPanel(new BorderLayout(0, 10));
        leftColumn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Musical Information"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(300, 400));
        
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
                
                double scale = Math.max(
                    (double) getWidth() / image.getWidth(),
                    (double) getHeight() / image.getHeight()
                );
                
                int scaledWidth = (int) (image.getWidth() * scale);
                int scaledHeight = (int) (image.getHeight() * scale);
                
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;
                
                g2d.drawImage(image, x, y, scaledWidth, scaledHeight, this);
            }
        };
        imageLabel.setPreferredSize(new Dimension(300, 400));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        leftColumn.add(imagePanel, BorderLayout.CENTER);
        
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
        
        JPanel rightColumn = new JPanel();
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        rightColumn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Booking Options"),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        JPanel bookingPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        
        dateCombo = new JComboBox<>(new String[]{"2024-03-20", "2024-03-21", "2024-03-22"});
        timeCombo = new JComboBox<>(new String[]{"19:00", "20:00", "21:00"});
        ticketTypeCombo = new JComboBox<>(TicketType.values());
        seatsCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        totalPriceLabel = new JLabel("£0.00");
        
        bookingPanel.add(new JLabel("Select Date:"));
        bookingPanel.add(dateCombo);
        
        bookingPanel.add(new JLabel("Select Time:"));
        bookingPanel.add(timeCombo);
        
        bookingPanel.add(new JLabel("Ticket Type:"));
        bookingPanel.add(ticketTypeCombo);
        
        bookingPanel.add(new JLabel("Number of Seats:"));
        bookingPanel.add(seatsCombo);
        
        bookingPanel.add(new JLabel("Total Price:"));
        bookingPanel.add(totalPriceLabel);
        
        ActionListener priceUpdater = e -> {
            Musical musical = orderManager.getSelectedMusical();
            if (musical != null) {
                int seats = (Integer) seatsCombo.getSelectedItem();
                TicketType ticketType = (TicketType) ticketTypeCombo.getSelectedItem();
                double total = musical.getBasePrice() * seats * ticketType.getPriceMultiplier();
                totalPriceLabel.setText(String.format("£%.2f", total));
            }
        };

        seatsCombo.addActionListener(priceUpdater);
        ticketTypeCombo.addActionListener(priceUpdater);
        
        rightColumn.add(bookingPanel);
        rightColumn.add(Box.createVerticalGlue());
        
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Book Tickets");
        submitButton.addActionListener(e -> {
            Musical musical = orderManager.getSelectedMusical();
            if (musical != null) {
                orderManager.setBookingDetails(
                    musical,
                    (String) dateCombo.getSelectedItem(),
                    (String) timeCombo.getSelectedItem(),
                    (TicketType) ticketTypeCombo.getSelectedItem(),
                    (Integer) seatsCombo.getSelectedItem(),
                    Double.parseDouble(totalPriceLabel.getText().replace("£", ""))
                );
                
                mainFrame.getConfirmationPanel().updateConfirmationDetails();
                Helpers.switchToPanel(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "PURCHASE_CONFIRMATION");
            }
        });
        buttonPanel.add(submitButton);
        rightColumn.add(buttonPanel);
        
        mainContainer.add(leftColumn);
        mainContainer.add(rightColumn);
        
        add(mainContainer, BorderLayout.CENTER);
    }

    public void updateMusicalDetails() {
        Musical musical = orderManager.getSelectedMusical();
        if (musical != null) {
            titleLabel.setText(musical.getTitle());
            descriptionLabel.setText(musical.getDescription());
            priceLabel.setText(String.format("Base Price: £%.2f", musical.getBasePrice()));
            durationLabel.setText(String.format("Duration: %d minutes", musical.getDurationMinutes()));
            
            try {
                URL url = new URL(musical.getImagePath());
                BufferedImage originalImage = ImageIO.read(url);
                imageLabel.setIcon(new ImageIcon(originalImage));
                imageLabel.revalidate();
                imageLabel.repaint();
            } catch (Exception e) {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not available");
                e.printStackTrace(); 
            }
        }
    }

    private void resetForm() {
        dateCombo.setSelectedIndex(0);
        timeCombo.setSelectedIndex(0);
        ticketTypeCombo.setSelectedIndex(0);
        seatsCombo.setSelectedIndex(0);
        totalPriceLabel.setText("£0.00");
    }
}
