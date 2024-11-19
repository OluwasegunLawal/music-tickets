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

public class OrderManagerTest {
    private musicals.OrderManager orderManager;
    private Musical testMusical;
    private ShowTime testShowTime;
    private Ticket testTicket;
    private static final String TEST_DATE = "2024-03-20";
    private static final String TEST_TIME = "19:00";

    @Before
    public void setUp() {
        orderManager = new OrderManager();
        testMusical = new Musical(1, "Test Musical", "Description", 50.0, 120);
        
        // Create a ShowTime instance
        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 20, 19, 0);
        testShowTime = new ShowTime(1, testMusical, dateTime, 100);
        
        // Create a Ticket instance
        testTicket = new Ticket(1, testShowTime, TicketType.ADULT, "A1");
    }

    @Test
    public void testInitialState() {
        assertNull("Initial selected musical should be null", orderManager.getSelectedMusical());
        assertNull("Initial booking date should be null", orderManager.getBookingDate());
        assertNull("Initial booking time should be null", orderManager.getBookingTime());
        assertEquals("Initial booking seats should be 0", 0, orderManager.getBookingSeats());
        assertEquals("Initial booking total price should be 0.0", 0.0, orderManager.getBookingTotalPrice(), 0.001);
    }

    @Test
    public void testSetSelectedMusical() {
        orderManager.setSelectedMusical(testMusical);
        assertEquals("Selected musical should match test musical", testMusical, orderManager.getSelectedMusical());
    }

    @Test
    public void testClearOrder() {
        // Set up some data first
        orderManager.setSelectedMusical(testMusical);
        orderManager.addTicket(testTicket);
        
        // Clear the order
        orderManager.clearOrder();
        
        assertNull("Selected musical should be null after clearing", orderManager.getSelectedMusical());
    }

    @Test
    public void testSetBookingDetails() {
        TicketType ticketType = TicketType.ADULT;
        int seats = 2;
        double totalPrice = 100.0;

        orderManager.setBookingDetails(testMusical, TEST_DATE, TEST_TIME, 
                                     ticketType, seats, totalPrice);

        assertEquals("Musical should match", testMusical, orderManager.getSelectedMusical());
        assertEquals("Date should match", TEST_DATE, orderManager.getBookingDate());
        assertEquals("Time should match", TEST_TIME, orderManager.getBookingTime());
        assertEquals("Ticket type should match", ticketType, orderManager.getBookingTicketType());
        assertEquals("Seats should match", seats, orderManager.getBookingSeats());
        assertEquals("Total price should match", totalPrice, orderManager.getBookingTotalPrice(), 0.001);
    }

    @Test
    public void testSetBookingDetailsWithNullValues() {
        orderManager.setBookingDetails(null, null, null, null, 0, 0.0);

        assertNull("Musical should be null", orderManager.getSelectedMusical());
        assertNull("Date should be null", orderManager.getBookingDate());
        assertNull("Time should be null", orderManager.getBookingTime());
        assertNull("Ticket type should be null", orderManager.getBookingTicketType());
        assertEquals("Seats should be 0", 0, orderManager.getBookingSeats());
        assertEquals("Total price should be 0.0", 0.0, orderManager.getBookingTotalPrice(), 0.001);
    }
}