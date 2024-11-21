package musicals;

import musicals.models.Musical;
import musicals.models.Ticket;
import musicals.models.TicketType;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Ticket> selectedTickets;
    private Musical selectedMusical;
    private String bookingDate;
    private String bookingTime;
    private TicketType bookingTicketType;
    private int bookingSeats;
    private double bookingTotalPrice;
    
    public OrderManager() {
        selectedTickets = new ArrayList<>();
    }
    
    public void addTicket(Ticket ticket) {
        selectedTickets.add(ticket);
    }
    
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
    
    public void setBookingDetails(Musical musical, String date, String time,
                                  TicketType ticketType, int seats, double totalPrice) {
        this.selectedMusical = musical;
        this.bookingDate = date;
        this.bookingTime = time;
        this.bookingTicketType = ticketType;
        this.bookingSeats = seats;
        this.bookingTotalPrice = totalPrice;
    }
    
    public String getBookingDate() { return bookingDate; }
    public String getBookingTime() { return bookingTime; }
    public TicketType getBookingTicketType() { return bookingTicketType; }
    public int getBookingSeats() { return bookingSeats; }
    public double getBookingTotalPrice() { return bookingTotalPrice; }
}