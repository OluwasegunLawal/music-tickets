package app.panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;

import app.MainFrame;
import app.OrderManager;
import app.utils.Helpers;

public class PurchaseConfirmationPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;

    public PurchaseConfirmationPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        mainFrame.setTitle("Purchase Confirmation");
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "Purchase Confirmation");
        
        // Create center panel for purchase details with padding
        JButton backButton = new JButton("Back");
        JPanel subHeaderContainer = new JPanel(new BorderLayout());
        subHeaderContainer.add(backButton, BorderLayout.WEST);

        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(5, 1, 2, 2));
        
        // Add labels for purchase information
        detailsPanel.add(new JLabel("Musical:"));
        detailsPanel.add(new JLabel("Date:"));
        detailsPanel.add(new JLabel("Seats:"));
        detailsPanel.add(new JLabel("Ticket Type:"));
        detailsPanel.add(new JLabel("Price:"));
        
        // Add details panel to content container
        contentContainer.add(detailsPanel, BorderLayout.CENTER);
        
        // Create button container
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