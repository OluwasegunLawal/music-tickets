package app.models;

public enum TicketType {
    ADULT(1.0),      // No discount
    SENIOR(0.8),     // 20% discount
    STUDENT(0.7);    // 30% discount
    
    private final double priceMultiplier;
    
    TicketType(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
    
    public double getPriceMultiplier() {
        return priceMultiplier;
    }
} 