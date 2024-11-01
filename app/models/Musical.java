package app.models;

public class Musical {
    private int id;
    private String title;
    private String description;
    private double basePrice;
    private int durationMinutes;
    
    public Musical(int id, String title, String description, double basePrice, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.basePrice = basePrice;
        this.durationMinutes = durationMinutes;
    }
} 