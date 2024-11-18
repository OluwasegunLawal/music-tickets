package musicals;

import musicals.models.Musical;
import musicals.models.Ticket;
import musicals.models.TicketType;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the ticket ordering process for musicals, including ticket selection,
 * musical selection, and show time selection.
 */
public class OrderManager {
    // List to store tickets selected by the customer
    private List<Ticket> selectedTickets;
    // Currently selected musical for the order
    private Musical selectedMusical;
    // Selected show time for the order
    private String bookingDate;
    private String bookingTime;
    private TicketType bookingTicketType;
    private int bookingSeats;
    private double bookingTotalPrice;
    
    /**
     * Constructs a new OrderManager with an empty list of selected tickets.
     */
    public OrderManager() {
        selectedTickets = new ArrayList<>();
    }
    
    /**
     * Adds a ticket to the current order.
     * @param ticket The ticket to be added to the order
     */
    public void addTicket(Ticket ticket) {
        selectedTickets.add(ticket);
    }
    
    /**
     * Clears the current order by removing all selected tickets,
     * selected musical, and selected show time.
     */
    public void clearOrder() {
        selectedTickets.clear();
        selectedMusical = null;
    }
    
    public void setSelectedMusical(Musical musical) {
        this.selectedMusical = musical;
    }
    
    public Musical getSelectedMusical() {
        return selectedMusical;
    }
    
    /**
     * Sets all booking details for the current transaction in one method call.
     * This includes the musical, date, time, ticket type, number of seats, and total price.
     * 
     * @param musical The selected musical performance
     * @param date The selected show date
     * @param time The selected show time
     * @param ticketType The type of tickets being purchased (e.g., Adult, Child)
     * @param seats The number of seats being booked
     * @param totalPrice The total price for all tickets
     */
    public void setBookingDetails(Musical musical, String date, String time,
                                  TicketType ticketType, int seats, double totalPrice) {
        this.selectedMusical = musical;
        this.bookingDate = date;
        this.bookingTime = time;
        this.bookingTicketType = ticketType;
        this.bookingSeats = seats;
        this.bookingTotalPrice = totalPrice;
    }
    
    // Getter methods
    public String getBookingDate() { return bookingDate; }
    public String getBookingTime() { return bookingTime; }
    public TicketType getBookingTicketType() { return bookingTicketType; }
    public int getBookingSeats() { return bookingSeats; }
    public double getBookingTotalPrice() { return bookingTotalPrice; }
    
    // Add getters and setters as needed
} 