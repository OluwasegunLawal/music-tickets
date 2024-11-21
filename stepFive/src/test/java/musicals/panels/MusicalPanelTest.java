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

class MusicalPanelTest {
    
    private MusicalPanel musicalPanel;
    
    @Mock
    private MainFrame mainFrame;
    
    @Mock
    private OrderManager orderManager;
    
    @Mock
    private CardLayout cardLayout;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mainFrame.getCardLayout()).thenReturn(cardLayout);
        when(mainFrame.getMainContainer()).thenReturn(new JPanel());
        musicalPanel = new MusicalPanel(mainFrame, orderManager);
    }

    @Test
    void testInitializeMusicals() throws Exception {
        Field musicalsField = MusicalPanel.class.getDeclaredField("musicals");
        musicalsField.setAccessible(true);
        Map<Integer, Musical> musicals = (Map<Integer, Musical>) musicalsField.get(musicalPanel);
        
        assertNotNull(musicals);
        assertEquals(8, musicals.size()); 
        
        Musical phantom = musicals.get(1);
        assertEquals("The Phantom of the Opera", phantom.getTitle());
        assertEquals(89.99, phantom.getBasePrice());
        assertEquals(150, phantom.getDurationMinutes());
    }

    @Test
    void testSearchBoxInitialization() throws Exception {
        Field searchBoxField = MusicalPanel.class.getDeclaredField("searchBox");
        searchBoxField.setAccessible(true);
        JComboBox<String> searchBox = (JComboBox<String>) searchBoxField.get(musicalPanel);
        
        assertNotNull(searchBox);
        assertEquals(9, searchBox.getItemCount()); // "All Musicals" + 8 musical titles
        assertEquals("All Musicals", searchBox.getItemAt(0));
    }

    @Test
    void testFilterMusicals() throws Exception {
        Field searchBoxField = MusicalPanel.class.getDeclaredField("searchBox");
        searchBoxField.setAccessible(true);
        JComboBox<String> searchBox = (JComboBox<String>) searchBoxField.get(musicalPanel);
        
        Field countPanelField = MusicalPanel.class.getDeclaredField("countPanel");
        countPanelField.setAccessible(true);
        JPanel countPanel = (JPanel) countPanelField.get(musicalPanel);

        searchBox.setSelectedItem("Phantom");
        
        Thread.sleep(100);
        
        assertTrue(countPanel.getComponentCount() < 8);
    }

    @Test
    void testShowAllMusicals() throws Exception {
        Field searchBoxField = MusicalPanel.class.getDeclaredField("searchBox");
        searchBoxField.setAccessible(true);
        JComboBox<String> searchBox = (JComboBox<String>) searchBoxField.get(musicalPanel);
        
        Field countPanelField = MusicalPanel.class.getDeclaredField("countPanel");
        countPanelField.setAccessible(true);
        JPanel countPanel = (JPanel) countPanelField.get(musicalPanel);

        searchBox.setSelectedItem("All Musicals");
        
        Thread.sleep(100);
        
        assertEquals(8, countPanel.getComponentCount());
    }

    @Test
    void testScheduleButtonAction() throws Exception {
        Field musicalsField = MusicalPanel.class.getDeclaredField("musicals");
        musicalsField.setAccessible(true);
        Map<Integer, Musical> musicals = (Map<Integer, Musical>) musicalsField.get(musicalPanel);
        
        JPanel musicalDetailsPanel = mock(MusicalDetailsPanel.class);
        when(mainFrame.getMusicalDetailsPanel()).thenReturn((MusicalDetailsPanel) musicalDetailsPanel);

        Musical firstMusical = musicals.get(1);
        
        verify(orderManager, never()).setSelectedMusical(firstMusical);
        verify(mainFrame, never()).getMusicalDetailsPanel();
    }
} 