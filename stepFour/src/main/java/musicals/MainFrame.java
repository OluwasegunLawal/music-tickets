package musicals;

import musicals.panels.MusicalDetailsPanel;
import musicals.panels.MusicalPanel;
import musicals.panels.PurchaseConfirmationPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window that manages the display of different panels using CardLayout.
 * This frame serves as the container for all main UI components of the musical booking system.
 */
public class MainFrame extends JFrame {

    // Constants for window dimensions and navigation
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String TITLE = "London Musicals";
    // Card names for different panels in the CardLayout
    private static final String MUSICALS_PANEL = "MUSICALS";
    private static final String MUSICAL_DETAILS_PANEL = "MUSICAL_DETAILS";
    private static final String PURCHASE_CONFIRMATION_PANEL = "PURCHASE_CONFIRMATION";

    // Layout managers and containers
    private CardLayout cardLayout;
    private JPanel mainContainer;
    
    // Core components for managing the application state and UI panels
    private OrderManager orderManager;
    private MusicalPanel musicalPanel;
    private MusicalDetailsPanel musicalDetailsPanel;
    private PurchaseConfirmationPanel purchaseConfirmationPanel;

    /**
     * Constructor initializes the main frame and sets up all necessary panels
     */
    public MainFrame() {
        initializeFrame();
        initializePanels();
    }

    /**
     * Sets up the main frame properties including size, title, and layout
     */
    private void initializeFrame() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        add(mainContainer);
    }

    /**
     * Initializes all panels and their dependencies
     * Note: Panel addition to mainContainer is currently commented out
     */
    private void initializePanels() {
        orderManager = new OrderManager();

        musicalPanel = new MusicalPanel(this, orderManager);
        musicalDetailsPanel = new MusicalDetailsPanel(this, orderManager);
        purchaseConfirmationPanel = new PurchaseConfirmationPanel(this, orderManager);

        mainContainer.add(MUSICALS_PANEL, musicalPanel);
        mainContainer.add(MUSICAL_DETAILS_PANEL, musicalDetailsPanel);
         mainContainer.add(PURCHASE_CONFIRMATION_PANEL, purchaseConfirmationPanel);
    }

    /**
     * @return The CardLayout used for managing panel transitions
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * @return The main container panel that holds all other panels
     */
    public JPanel getMainContainer() {
        return mainContainer;
    }

    /**
     * Entry point of the application
     * Creates and displays the main frame on the Event Dispatch Thread
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    public MusicalDetailsPanel getMusicalDetailsPanel() {
        return musicalDetailsPanel;
    }

    public PurchaseConfirmationPanel getConfirmationPanel() {
        return purchaseConfirmationPanel;
    }
}
