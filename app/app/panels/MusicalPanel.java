package app.panels;

import app.MainFrame;
import app.OrderManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import app.models.Musical;
import app.models.ShowTime;
import app.utils.Helpers;

import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class MusicalPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;
    private JList<Musical> musicalList;
    private JComboBox<LocalDate> dateSelector;
    private JList<ShowTime> showTimeList;

    public MusicalPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), mainFrame.getMainContainer(), "Welcome to London Musicals");
        add(headerContainer, BorderLayout.NORTH);

        // Add panel for count boxes
        JPanel countPanel = new JPanel();
        for (int i = 1; i <= 8; i++) {
            // Create a container for each box and its label
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BorderLayout());
            
            // Create the box with the number
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setPreferredSize(new Dimension(200, 200));
            box.add(new JLabel(String.valueOf(i)));
            
            // Add box and text to container
            containerPanel.add(box, BorderLayout.CENTER);
            containerPanel.add(new JLabel("Musical " + i, JLabel.LEFT), BorderLayout.SOUTH);
            
            countPanel.add(containerPanel);
        }
        add(countPanel, BorderLayout.CENTER);
    }
} 