package app;

import javax.swing.*;
import java.awt.*;

import app.panels.MusicalDetailsPanel;
import app.panels.MusicalPanel;
import app.panels.PurchaseConfirmationPanel;

public class MainFrame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final String TITLE = "London Musicals";
    private static final String MUSICALS_PANEL = "MUSICALS";
    private static final String MUSICAL_DETAILS_PANEL = "MUSICAL_DETAILS";
    private static final String PURCHASE_CONFIRMATION_PANEL = "PURCHASE_CONFIRMATION";

    private CardLayout cardLayout;
    private JPanel mainContainer;

    private OrderManager orderManager;
    private MusicalPanel musicalPanel;
    private MusicalDetailsPanel musicalDetailsPanel;
    private PurchaseConfirmationPanel purchaseConfirmationPanel;

    public MainFrame() {
        initializeFrame();
        initializePanels();
    }

    private void initializeFrame() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        add(mainContainer);
    }

    private void initializePanels() {
        orderManager = new OrderManager();

        musicalPanel = new MusicalPanel(this, orderManager);
        musicalDetailsPanel = new MusicalDetailsPanel(this, orderManager);
        purchaseConfirmationPanel = new PurchaseConfirmationPanel(this, orderManager);

//         mainContainer.add(MUSICALS_PANEL, musicalPanel);
//         mainContainer.add(MUSICAL_DETAILS_PANEL, musicalDetailsPanel);
//         mainContainer.add(PURCHASE_CONFIRMATION_PANEL, purchaseConfirmationPanel);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
