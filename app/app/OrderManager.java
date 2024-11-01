package app;

import java.util.ArrayList;
import java.util.List;

import app.models.Musical;
import app.models.ShowTime;
import app.models.Ticket;

public class OrderManager {
    private List<Ticket> selectedTickets;
    private Musical selectedMusical;
    private ShowTime selectedShowTime;
    
    public OrderManager() {
        selectedTickets = new ArrayList<>();
    }
    
    public void addTicket(Ticket ticket) {
        selectedTickets.add(ticket);
    }
    
    public double calculateTotal() {
        return 0;
        // return selectedTickets.stream()
        //         .mapToDouble(Ticket::getFinalPrice)
        //         .sum();
    }
    
    public void clearOrder() {
        selectedTickets.clear();
        selectedMusical = null;
        selectedShowTime = null;
    }
    
    // Add getters and setters as needed
} 