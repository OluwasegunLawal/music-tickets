package musicals.models;

public enum TicketType {
    ADULT(1.0),
    SENIOR(0.8),
    STUDENT(0.7);
    
    private final double priceMultiplier;
    
    TicketType(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
    
    public double getPriceMultiplier() {
        return priceMultiplier;
    }
} 