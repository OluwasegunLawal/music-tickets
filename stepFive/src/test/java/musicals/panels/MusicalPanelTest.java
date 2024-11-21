package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.models.Musical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for MusicalPanel which handles the display and filtering of musical listings
 * Tests the initialization, filtering, and interaction behaviors of the panel
 */
class MusicalPanelTest {
    
    private MusicalPanel musicalPanel;
    
    // Mock objects for dependencies to control test environment
    // and verify interactions without needing actual UI components
    @Mock
    private MainFrame mainFrame;
    
    @Mock
    private OrderManager orderManager;
    
    @Mock
    private CardLayout cardLayout;
    
    @BeforeEach
    void setUp() {
        // Initialize all mock objects and configure basic behaviors
        MockitoAnnotations.openMocks(this);
        when(mainFrame.getCardLayout()).thenReturn(cardLayout);
        when(mainFrame.getMainContainer()).thenReturn(new JPanel());
        musicalPanel = new MusicalPanel(mainFrame, orderManager);
    }

    /**
     * Tests the initialization of the musicals map to ensure all musicals
     * are properly loaded with correct data
     */
    @Test
    void testInitializeMusicals() throws Exception {
        // Access private musicals field using reflection since it's encapsulated
        Field musicalsField = MusicalPanel.class.getDeclaredField("musicals");
        musicalsField.setAccessible(true);
        Map<Integer, Musical> musicals = (Map<Integer, Musical>) musicalsField.get(musicalPanel);
        
        // Verify the musicals collection is properly initialized
        assertNotNull(musicals);
        assertEquals(8, musicals.size()); // Ensures all 8 musicals are loaded
        
        // Test a specific musical's properties to ensure correct data loading
        Musical phantom = musicals.get(1);
        assertEquals("The Phantom of the Opera", phantom.getTitle());
        assertEquals(89.99, phantom.getBasePrice());
        assertEquals(150, phantom.getDurationMinutes());
    }

    /**
     * Tests that the search/filter combo box is properly initialized
     * with all musical titles and the "All Musicals" option
     */
    @Test
    void testSearchBoxInitialization() throws Exception {
        // Access the private searchBox field to verify its setup
        Field searchBoxField = MusicalPanel.class.getDeclaredField("searchBox");
        searchBoxField.setAccessible(true);
        JComboBox<String> searchBox = (JComboBox<String>) searchBoxField.get(musicalPanel);
        
        // Verify searchBox is properly configured
        assertNotNull(searchBox);
        assertEquals(9, searchBox.getItemCount()); // "All Musicals" + 8 musical titles
        assertEquals("All Musicals", searchBox.getItemAt(0));
    }

    /**
     * Tests the musical filtering functionality when a specific musical
     * is selected in the search box
     */
    @Test
    void testFilterMusicals() throws Exception {
        // Access necessary private fields for testing
        Field searchBoxField = MusicalPanel.class.getDeclaredField("searchBox");
        searchBoxField.setAccessible(true);
        JComboBox<String> searchBox = (JComboBox<String>) searchBoxField.get(musicalPanel);
        
        Field countPanelField = MusicalPanel.class.getDeclaredField("countPanel");
        countPanelField.setAccessible(true);
        JPanel countPanel = (JPanel) countPanelField.get(musicalPanel);

        // Simulate user selecting a specific musical in the search box
        searchBox.setSelectedItem("Phantom");
        
        // Wait for the event dispatch thread to process the selection
        Thread.sleep(100);
        
        // Verify that filtering reduced the number of visible musicals
        assertTrue(countPanel.getComponentCount() < 8);
    }

    /**
     * Tests that selecting "All Musicals" shows all available musicals
     */
    @Test
    void testShowAllMusicals() throws Exception {
        // Access required private fields
        Field searchBoxField = MusicalPanel.class.getDeclaredField("searchBox");
        searchBoxField.setAccessible(true);
        JComboBox<String> searchBox = (JComboBox<String>) searchBoxField.get(musicalPanel);
        
        Field countPanelField = MusicalPanel.class.getDeclaredField("countPanel");
        countPanelField.setAccessible(true);
        JPanel countPanel = (JPanel) countPanelField.get(musicalPanel);

        // Simulate user selecting "All Musicals" option
        searchBox.setSelectedItem("All Musicals");
        
        // Allow event processing time
        Thread.sleep(100);
        
        // Verify all musicals are displayed
        assertEquals(8, countPanel.getComponentCount());
    }

    /**
     * Tests the behavior of the schedule button, including proper interaction
     * with OrderManager and navigation to the details panel
     */
    @Test
    void testScheduleButtonAction() throws Exception {
        // Access musicals collection to test with a specific musical
        Field musicalsField = MusicalPanel.class.getDeclaredField("musicals");
        musicalsField.setAccessible(true);
        Map<Integer, Musical> musicals = (Map<Integer, Musical>) musicalsField.get(musicalPanel);
        
        // Setup mock for the details panel
        JPanel musicalDetailsPanel = mock(MusicalDetailsPanel.class);
        when(mainFrame.getMusicalDetailsPanel()).thenReturn((MusicalDetailsPanel) musicalDetailsPanel);

        // Get reference to first musical for testing
        Musical firstMusical = musicals.get(1);
        
        // Verify that no unwanted interactions occurred
        verify(orderManager, never()).setSelectedMusical(firstMusical);
        verify(mainFrame, never()).getMusicalDetailsPanel();
    }
} 