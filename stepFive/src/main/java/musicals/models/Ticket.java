package musicals.models;

public class Ticket {
    private int id;
    private ShowTime showTime;
    private TicketType ticketType;
    private String seatNumber;
    private double finalPrice;
    
    public Ticket(int id, ShowTime showTime, TicketType ticketType, String seatNumber) {
        this.id = id;
        this.showTime = showTime;
        this.ticketType = ticketType;
        this.seatNumber = seatNumber;
        this.finalPrice = calculatePrice();
    }
    
    private double calculatePrice() {
        return 0;
    }
}