package app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private List<Ticket> tickets;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private double totalAmount;
    private LocalDateTime orderDate;
    
    public Order(String customerName, String customerEmail, String customerPhone) {
        this.orderId = UUID.randomUUID().toString();
        this.tickets = new ArrayList<>();
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.orderDate = LocalDateTime.now();
    }
    
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        calculateTotal();
    }
    
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        calculateTotal();
    }
    
    private void calculateTotal() {
        this.totalAmount = 0;
        // this.totalAmount = tickets.stream()
        //         .mapToDouble(Ticket::getFinalPrice)
        //         .sum();
    }
    
    // Getters and setters
    public String getOrderId() { return orderId; }
    
    public List<Ticket> getTickets() { return tickets; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    
    public double getTotalAmount() { return totalAmount; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    
} 