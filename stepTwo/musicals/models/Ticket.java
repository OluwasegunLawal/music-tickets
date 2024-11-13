package musicals.models;

/**
 * Represents a ticket for a musical show.
 * Contains information about the show time, ticket type, seat, and price.
 */
public class Ticket {
    /** Unique identifier for the ticket */
    private int id;
    /** The show time associated with this ticket */
    private ShowTime showTime;
    /** Type of ticket (e.g., Adult, Child, Senior) */
    private TicketType ticketType;
    /** Seat number assigned to this ticket */
    private String seatNumber;
    /** Final calculated price of the ticket */
    private double finalPrice;
    
    /**
     * Creates a new ticket with the specified details.
     * @param id Unique identifier for the ticket
     * @param showTime The show time for this ticket
     * @param ticketType The type of ticket
     * @param seatNumber The assigned seat number
     */
    public Ticket(int id, ShowTime showTime, TicketType ticketType, String seatNumber) {
        this.id = id;
        this.showTime = showTime;
        this.ticketType = ticketType;
        this.seatNumber = seatNumber;
        this.finalPrice = calculatePrice();
    }
    
    /**
     * Calculates the final price of the ticket.
     * Currently returns 0, but should multiply the musical's base price
     * by the ticket type's price multiplier.
     * @return The calculated price for the ticket
     */
    private double calculatePrice() {
        return 0;
        // return showTime.getMusical().getBasePrice() * ticketType.getPriceMultiplier();
    }
    
    // Getters and setters
    // ... (implement standard getters/setters for all fields)
} 