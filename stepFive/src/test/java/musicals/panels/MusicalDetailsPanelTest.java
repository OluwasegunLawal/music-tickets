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

class MusicalDetailsPanelTest {

    @Mock
    private MainFrame mainFrame;
    
    @Mock
    private OrderManager orderManager;
    
    @Mock
    private Musical musical;
    
    private MusicalDetailsPanel panel;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);
        when(mainFrame.getCardLayout()).thenReturn(cardLayout);
        when(mainFrame.getMainContainer()).thenReturn(container);
        
        when(musical.getTitle()).thenReturn("Test Musical");
        when(musical.getDescription()).thenReturn("Test Description");
        when(musical.getBasePrice()).thenReturn(50.0);
        when(musical.getDurationMinutes()).thenReturn(120);
        when(musical.getImagePath()).thenReturn("https://example.com/image.jpg");
        
        panel = new MusicalDetailsPanel(mainFrame, orderManager);
    }

    @Test
    void resetForm_ShouldResetAllFieldsToDefault() {
        invokePrivateMethod(panel, "resetForm");
        
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