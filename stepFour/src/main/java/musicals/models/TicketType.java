package musicals.models;

/**
 * Represents different types of tickets available for purchase with their associated price multipliers.
 * Each ticket type has a specific discount applied through its price multiplier.
 */
public enum TicketType {
    ADULT(1.0),      // Standard ticket price with no discount
    SENIOR(0.8),     // Senior ticket with 20% discount (multiplier 0.8)
    STUDENT(0.7);    // Student ticket with 30% discount (multiplier 0.7)
    
    /** The multiplier applied to the base ticket price to calculate the final price */
    private final double priceMultiplier;
    
    /**
     * Constructor for TicketType enum.
     * @param priceMultiplier The discount multiplier to be applied to the base ticket price
     */
    TicketType(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
    
    /**
     * Returns the price multiplier for this ticket type.
     * @return The multiplier value used to calculate the discounted price
     */
    public double getPriceMultiplier() {
        return priceMultiplier;
    }
} 