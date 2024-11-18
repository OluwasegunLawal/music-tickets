package musicals;

import musicals.models.Musical;
import musicals.models.ShowTime;
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
    private ShowTime selectedShowTime;
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
     * Calculates the total price of all selected tickets.
     * @return The sum of all ticket prices in the order
     * TODO: Implement the calculation using stream operation (currently returns 0)
     */
    public double calculateTotal() {
        return 0;
        // return selectedTickets.stream()
        //         .mapToDouble(Ticket::getFinalPrice)
        //         .sum();
    }
    
    /**
     * Clears the current order by removing all selected tickets,
     * selected musical, and selected show time.
     */
    public void clearOrder() {
        selectedTickets.clear();
        selectedMusical = null;
        selectedShowTime = null;
    }
    
    public void setSelectedMusical(Musical musical) {
        this.selectedMusical = musical;
    }
    
    public Musical getSelectedMusical() {
        return selectedMusical;
    }
    
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