package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.models.Musical;
import musicals.models.ShowTime;
import musicals.utils.Helpers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MusicalPanel extends JPanel {
    private MainFrame mainFrame;
    private OrderManager orderManager;
    private Map<Integer, Musical> musicals;
    private JComboBox<String> searchBox;
    private JPanel countPanel;
    private JButton uploadButton;

    public MusicalPanel(MainFrame mainFrame, OrderManager orderManager) {
        this.mainFrame = mainFrame;
        this.orderManager = orderManager;
        initializeMusicals();
        
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeMusicals() {
        musicals = new HashMap<>();
        
        // Add upload button
        uploadButton = new JButton("Upload Musicals CSV");
        uploadButton.addActionListener(e -> handleCsvUpload());
        
    }

    private void handleCsvUpload() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadMusicalsFromCsv(selectedFile);
        }
    }

    private void loadMusicalsFromCsv(File file) {
        musicals.clear(); // Clear existing musicals
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] values = line.split(",");
                if (values.length >= 6) {
                    int id = Integer.parseInt(values[0].trim());
                    String title = values[1].trim();
                    String description = values[2].trim();
                    double price = Double.parseDouble(values[3].trim());
                    int duration = Integer.parseInt(values[4].trim());
                    String imagePath = values[5].trim();
                    
                    Musical musical = new Musical(id, title, description, price, duration);
                    musical.setImagePath(imagePath);
                    musicals.put(id, musical);
                }
            }
            
            // Refresh the display
            refreshDisplay();
            
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Error reading CSV file. Please ensure the format is correct:\n" +
                "ID,Title,Description,Price,Duration,ImageURL",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshDisplay() {
        // Clear and repopulate the search box
        searchBox.removeAllItems();
        searchBox.addItem("All Musicals");
        musicals.values().forEach(musical -> searchBox.addItem(musical.getTitle()));
        
        // Refresh the musical panels
        filterMusicals("All Musicals");
    }

    private void initializeComponents() {
        // Create and add the header section with no back button
        JPanel headerContainer = Helpers.getHeaderContainer(mainFrame.getCardLayout(), 
            mainFrame.getMainContainer(), "Welcome to London Musicals", false, "");
        add(headerContainer, BorderLayout.NORTH);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchBox = new JComboBox<>();
        searchBox.setEditable(true);
        searchBox.setPreferredSize(new Dimension(200, 25));
        
        // Add all musical titles to the search box
        searchBox.addItem("All Musicals");
        musicals.values().forEach(musical -> searchBox.addItem(musical.getTitle()));
        
        // Add search listener
        searchBox.addActionListener(e -> {
            String selected = (String) searchBox.getSelectedItem();
            filterMusicals(selected);
        });
        
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchBox);
        
        // Add upload button to search panel
        searchPanel.add(uploadButton);
        
        // Add search panel below header
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(headerContainer, BorderLayout.NORTH);
        topContainer.add(searchPanel, BorderLayout.CENTER);
        add(topContainer, BorderLayout.NORTH);

        // Create panel to hold musical selection boxes with padding
        countPanel = new JPanel();
        countPanel.setLayout(new GridLayout(2, 4, 20, 20)); // Increased gaps between boxes
        countPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        for (int i = 1; i <= 8; i++) {
            Musical musical = musicals.get(i);
            addMusicalPanel(musical);
        }
        add(countPanel, BorderLayout.CENTER);
    }

    private void filterMusicals(String searchTerm) {
        // Remove existing panels
        countPanel.removeAll();
        
        if (searchTerm == null || searchTerm.isEmpty() || searchTerm.equals("All Musicals")) {
            // Show all musicals
            musicals.values().stream()
                .filter(musical -> musical != null)
                .forEach(this::addMusicalPanel);
        } else {
            // Show only matching musicals
            musicals.values().stream()
                .filter(musical -> musical != null && musical.getTitle().toLowerCase()
                    .contains(searchTerm.toLowerCase()))
                .forEach(this::addMusicalPanel);
        }
        
        countPanel.revalidate();
        countPanel.repaint();
    }

    private void addMusicalPanel(Musical musical) {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setPreferredSize(new Dimension(300, 300));
        
        try {
            // Load and scale the image
            URL url = new URL(musical.getImagePath());
            BufferedImage originalImage = ImageIO.read(url);
            
            // Create a custom JLabel for the image that maintains aspect ratio and fills
            JLabel imageLabel = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                                       RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    
                    // Calculate dimensions to cover the entire area
                    double scale = Math.max(
                        (double) getWidth() / originalImage.getWidth(),
                        (double) getHeight() / originalImage.getHeight()
                    );
                    
                    int scaledWidth = (int) (originalImage.getWidth() * scale);
                    int scaledHeight = (int) (originalImage.getHeight() * scale);
                    
                    // Center the image
                    int x = (getWidth() - scaledWidth) / 2;
                    int y = (getHeight() - scaledHeight) / 2;
                    
                    g2d.drawImage(originalImage, x, y, scaledWidth, scaledHeight, this);
                }
            };
            
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            containerPanel.add(imageLabel, BorderLayout.CENTER);
            
        } catch (Exception e) {
            // Fallback to empty box if image loading fails
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setPreferredSize(new Dimension(300, 300));
            containerPanel.add(box, BorderLayout.CENTER);
        }
            
        // Create info panel for details below the box
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        if (musical != null) {
            // Create labels and set their alignment to LEFT
            JLabel titleLabel = new JLabel(musical.getTitle());
            JLabel priceLabel = new JLabel(String.format("£%.2f", musical.getBasePrice()));
            JLabel durationLabel = new JLabel(musical.getDurationMinutes() + " minutes");
            JButton scheduleButton = new JButton("Show schedule");
            scheduleButton.addActionListener(e -> {
                orderManager.setSelectedMusical(musical);  // Store the selected musical
                mainFrame.getMusicalDetailsPanel().updateMusicalDetails(); // Update the details panel
                Helpers.switchToPanel(
                        mainFrame.getCardLayout(),
                        mainFrame.getMainContainer(),
                        "MUSICAL_DETAILS"
                );
            });

            // Set alignment for all components
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            durationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            scheduleButton.setAlignmentX(Component.LEFT_ALIGNMENT);

            infoPanel.add(titleLabel);
            infoPanel.add(priceLabel);
            infoPanel.add(durationLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(scheduleButton);
        }

        containerPanel.add(infoPanel, BorderLayout.SOUTH);
        
        countPanel.add(containerPanel);
    }

    private void addDefaultMusicals() {
        Musical phantom = new Musical(1, "The Phantom of the Opera", 
            "A mysterious phantom haunts an opera house in Paris", 89.99, 150);
        phantom.setImagePath("https://ents24.imgix.net/image/000/359/294/2d712f2d711e6e6e683dce9fdb53aa0c639562c0.jpg?auto=format&fp-x=0.532&fp-y=0.023&crop=focalpoint&w=375&h=400&q=40");
        musicals.put(1, phantom);
        
        Musical lesMis = new Musical(2, "Les Misérables", 
            "Epic tale of broken dreams and redemption", 79.99, 165);
        lesMis.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/6/67/LesMisLogo.png/220px-LesMisLogo.png");
        musicals.put(2, lesMis);
        
        Musical lionKing = new Musical(3, "The Lion King", 
            "Disney's beloved story comes to life on stage", 99.99, 150);
        lionKing.setImagePath("https://lumiere-a.akamaihd.net/v1/images/p_thelionking_19752_1_0b9de87b.jpeg?region=0%2C0%2C540%2C810");
        musicals.put(3, lionKing);
        
        Musical wicked = new Musical(4, "Wicked", 
            "The untold story of the witches of Oz", 89.99, 165);
        wicked.setImagePath("https://www.wickedthemusical.co.uk/wp-content/uploads/2022/06/Wicked_1080x1080_SEO-min.jpg");
        musicals.put(4, wicked);
        
        Musical hamilton = new Musical(5, "Hamilton", 
            "American history told through hip-hop and R&B", 129.99, 165);
        hamilton.setImagePath("https://cdn.londonandpartners.com/asset/hamilton-at-victoria-palace-theatre_hamilton-image-courtesy-of-cameron-mackintosh_88f258081c4e17e8e2725d7e393adc1e.jpg");
        musicals.put(5, hamilton);
        
        Musical mammaMia = new Musical(6, "Mamma Mia!", 
            "ABBA's hits tell a hilarious story of love", 69.99, 150);
        mammaMia.setImagePath("https://playbillstore.com/cdn/shop/files/MAMMA-MIA-MAGNET-ART-091423--1_06c4974a-ebfe-4d87-b67b-2c14ae9e22e0.jpg?v=1715071268");
        musicals.put(6, mammaMia);
        
        Musical chicago = new Musical(7, "Chicago", 
            "Murder, greed, corruption, and all that jazz", 79.99, 150);
        chicago.setImagePath("https://m.media-amazon.com/images/M/MV5BNmJmZDEyNTctOTM1MS00NWEwLWFkMjctMjhhODEzMjU1MjA0XkEyXkFqcGc@._V1_.jpg");
        musicals.put(7, chicago);
        
        Musical maryPoppins = new Musical(8, "Mary Poppins", 
            "Magical nanny brings joy to a troubled family", 69.99, 155);
        maryPoppins.setImagePath("https://m.media-amazon.com/images/I/71BNngHpwJL._AC_UF1000,1000_QL80_.jpg");
        musicals.put(8, maryPoppins);
    }
} 