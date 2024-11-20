package musicals;

import musicals.models.Musical;
import musicals.models.Ticket;
import musicals.models.TicketType;
import musicals.models.ShowTime;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test class for OrderManager which handles the booking state and order management
 * Tests the initialization, order manipulation, and booking detail management
 */
public class OrderManagerTest {
    // Core test objects used across multiple test cases
    private musicals.OrderManager orderManager;
    private Musical testMusical;
    private ShowTime testShowTime;
    private Ticket testTicket;
    
    // Constants for testing booking dates and times
    private static final String TEST_DATE = "2024-03-20";
    private static final String TEST_TIME = "19:00";

    /**
     * Sets up the test environment before each test method
     * Creates fresh instances of OrderManager and test objects to ensure test isolation
     */
    @Before
    public void setUp() {
        // Create a new OrderManager instance for each test
        orderManager = new OrderManager();
        
        // Initialize a test Musical with sample data
        testMusical = new Musical(1, "Test Musical", "Description", 50.0, 120);
        
        // Create a ShowTime instance for testing
        // Using a specific date/time to ensure consistent test behavior
        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 20, 19, 0);
        testShowTime = new ShowTime(1, testMusical, dateTime, 100);
        
        // Create a sample Ticket for testing order operations
        testTicket = new Ticket(1, testShowTime, TicketType.ADULT, "A1");
    }

    /**
     * Tests that OrderManager is properly initialized with null/empty values
     * Verifies the initial state before any operations are performed
     */
    @Test
    public void testInitialState() {
        // Verify all fields are initialized to their default values
        assertNull("Initial selected musical should be null", 
                  orderManager.getSelectedMusical());
        assertNull("Initial booking date should be null", 
                  orderManager.getBookingDate());
        assertNull("Initial booking time should be null", 
                  orderManager.getBookingTime());
        assertEquals("Initial booking seats should be 0", 
                    0, orderManager.getBookingSeats());
        assertEquals("Initial booking total price should be 0.0", 
                    0.0, orderManager.getBookingTotalPrice(), 0.001);
    }

    /**
     * Tests the musical selection functionality
     * Verifies that a musical can be properly selected and retrieved
     */
    @Test
    public void testSetSelectedMusical() {
        // Set a musical and verify it was stored correctly
        orderManager.setSelectedMusical(testMusical);
        assertEquals("Selected musical should match test musical", 
                    testMusical, orderManager.getSelectedMusical());
    }

    /**
     * Tests the order clearing functionality
     * Verifies that all order details are reset when cleared
     */
    @Test
    public void testClearOrder() {
        // First, set up some test data
        orderManager.setSelectedMusical(testMusical);
        orderManager.addTicket(testTicket);
        
        // Clear the order and verify the reset
        orderManager.clearOrder();
        
        // Verify that the musical selection was cleared
        assertNull("Selected musical should be null after clearing", 
                  orderManager.getSelectedMusical());
    }

    /**
     * Tests setting booking details with valid values
     * Verifies that all booking information is properly stored
     */
    @Test
    public void testSetBookingDetails() {
        // Test data for booking details
        TicketType ticketType = TicketType.ADULT;
        int seats = 2;
        double totalPrice = 100.0;

        // Set all booking details at once
        orderManager.setBookingDetails(testMusical, TEST_DATE, TEST_TIME, 
                                     ticketType, seats, totalPrice);

        // Verify all fields were set correctly
        assertEquals("Musical should match", 
                    testMusical, orderManager.getSelectedMusical());
        assertEquals("Date should match", 
                    TEST_DATE, orderManager.getBookingDate());
        assertEquals("Time should match", 
                    TEST_TIME, orderManager.getBookingTime());
        assertEquals("Ticket type should match", 
                    ticketType, orderManager.getBookingTicketType());
        assertEquals("Seats should match", 
                    seats, orderManager.getBookingSeats());
        assertEquals("Total price should match", 
                    totalPrice, orderManager.getBookingTotalPrice(), 0.001);
    }

    /**
     * Tests setting booking details with null/empty values
     * Verifies that the system handles null inputs appropriately
     */
    @Test
    public void testSetBookingDetailsWithNullValues() {
        // Set all booking details to null/zero values
        orderManager.setBookingDetails(null, null, null, null, 0, 0.0);

        // Verify that null values are handled correctly
        assertNull("Musical should be null", 
                  orderManager.getSelectedMusical());
        assertNull("Date should be null", 
                  orderManager.getBookingDate());
        assertNull("Time should be null", 
                  orderManager.getBookingTime());
        assertNull("Ticket type should be null", 
                  orderManager.getBookingTicketType());
        assertEquals("Seats should be 0", 
                    0, orderManager.getBookingSeats());
        assertEquals("Total price should be 0.0", 
                    0.0, orderManager.getBookingTotalPrice(), 0.001);
    }
}