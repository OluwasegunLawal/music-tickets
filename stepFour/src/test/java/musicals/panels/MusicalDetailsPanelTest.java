package musicals.panels;

import musicals.MainFrame;
import musicals.OrderManager;
import musicals.models.Musical;
import musicals.models.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for MusicalDetailsPanel
 * Tests the UI component behavior and data display functionality
 */
class MusicalDetailsPanelTest {

    // Mock objects are used instead of real implementations to:
    // 1. Control their behavior in tests
    // 2. Verify interactions
    // 3. Avoid dependencies on actual UI state or database
    @Mock
    private MainFrame mainFrame;
    
    @Mock
    private OrderManager orderManager;
    
    @Mock
    private Musical musical;
    
    private MusicalDetailsPanel panel;
    
    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations - this processes @Mock annotations
        // and creates mock instances for all annotated fields
        MockitoAnnotations.openMocks(this);
        
        // Setup MainFrame mock with required UI components
        // CardLayout is needed because the panel uses it for navigation
        // Container is required as it holds all the application panels
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);
        when(mainFrame.getCardLayout()).thenReturn(cardLayout);
        when(mainFrame.getMainContainer()).thenReturn(container);
        
        // Configure mock Musical with test data
        // These values will be used to verify that the UI correctly displays
        // all the musical information provided by the model
        when(musical.getTitle()).thenReturn("Test Musical");
        when(musical.getDescription()).thenReturn("Test Description");
        when(musical.getBasePrice()).thenReturn(50.0);
        when(musical.getDurationMinutes()).thenReturn(120);
        when(musical.getImagePath()).thenReturn("https://example.com/image.jpg");
        
        // Create a fresh instance of the panel for each test
        // This ensures test isolation and prevents state bleeding between tests
        panel = new MusicalDetailsPanel(mainFrame, orderManager);
    }

    @Test
    void resetForm_ShouldResetAllFieldsToDefault() {
        // Act
        invokePrivateMethod(panel, "resetForm");
        
        // Assert
        JComboBox<?> dateCombo = (JComboBox<?>) getPrivateField(panel, "dateCombo");
        JComboBox<?> timeCombo = (JComboBox<?>) getPrivateField(panel, "timeCombo");
        JComboBox<?> ticketTypeCombo = (JComboBox<?>) getPrivateField(panel, "ticketTypeCombo");
        JComboBox<?> seatsCombo = (JComboBox<?>) getPrivateField(panel, "seatsCombo");
        JLabel totalPriceLabel = (JLabel) getPrivateField(panel, "totalPriceLabel");
        
        assertEquals(0, dateCombo.getSelectedIndex());
        assertEquals(0, timeCombo.getSelectedIndex());
        assertEquals(0, ticketTypeCombo.getSelectedIndex());
        assertEquals(0, seatsCombo.getSelectedIndex());
        assertEquals("£0.00", totalPriceLabel.getText());
    }

    // Helper methods for accessing private fields and methods
    private Object getPrivateField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            fail("Could not access field: " + fieldName);
            return null;
        }
    }

    private void invokePrivateMethod(Object obj, String methodName) {
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(obj);
        } catch (Exception e) {
            fail("Could not invoke method: " + methodName);
        }
    }
}