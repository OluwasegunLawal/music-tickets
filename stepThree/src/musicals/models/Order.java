package musicals.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a customer's order for musical tickets.
 * Contains order details, customer information, and a collection of tickets.
 */
public class Order {
    // Unique identifier for the order
    private String orderId;
    // List of tickets in this order
    private List<Ticket> tickets;
    // Customer details
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    // Total cost of all tickets in the order
    private double totalAmount;
    // Timestamp when the order was created
    private LocalDateTime orderDate;
    
    /**
     * Creates a new order with customer information.
     * Initializes the order with a unique ID and current timestamp.
     *
     * @param customerName  The name of the customer
     * @param customerEmail The email address of the customer
     * @param customerPhone The phone number of the customer
     */
    public Order(String customerName, String customerEmail, String customerPhone) {
        this.orderId = UUID.randomUUID().toString();
        this.tickets = new ArrayList<>();
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.orderDate = LocalDateTime.now();
    }
    
    /**
     * Adds a ticket to the order and recalculates the total amount.
     *
     * @param ticket The ticket to add to the order
     */
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        calculateTotal();
    }
    
    /**
     * Removes a ticket from the order and recalculates the total amount.
     *
     * @param ticket The ticket to remove from the order
     */
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        calculateTotal();
    }
    
    /**
     * Calculates the total amount for all tickets in the order.
     * Currently implemented with a placeholder calculation.
     */
    private void calculateTotal() {
        this.totalAmount = 0;
        // this.totalAmount = tickets.stream()
        //         .mapToDouble(Ticket::getFinalPrice)
        //         .sum();
    }
    
    // Getters and setters with basic documentation
    /** @return The unique identifier of the order */
    public String getOrderId() { return orderId; }
    
    /** @return The list of tickets in the order */
    public List<Ticket> getTickets() { return tickets; }
    
    /** @return The customer's name */
    public String getCustomerName() { return customerName; }
    /** @param customerName The new customer name to set */
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    /** @return The customer's email address */
    public String getCustomerEmail() { return customerEmail; }
    /** @param customerEmail The new customer email to set */
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    
    /** @return The customer's phone number */
    public String getCustomerPhone() { return customerPhone; }
    /** @param customerPhone The new customer phone number to set */
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    
    /** @return The total amount for all tickets in the order */
    public double getTotalAmount() { return totalAmount; }
    
    /** @return The timestamp when the order was created */
    public LocalDateTime getOrderDate() { return orderDate; }
} 