package musicals.models;

/**
 * Represents a musical theater production with its basic information.
 * This class stores details about a musical including its identifier,
 * title, description, pricing, and duration.
 */
public class Musical {
    // Unique identifier for the musical
    private int id;
    
    // The title/name of the musical
    private String title;
    
    // A description of the musical's plot or content
    private String description;
    
    // The base ticket price for the musical in currency units
    private double basePrice;
    
    // The total runtime of the musical in minutes
    private int durationMinutes;
    
    /**
     * Constructs a new Musical with all required fields.
     * 
     * @param id              The unique identifier for the musical
     * @param title          The title of the musical
     * @param description    A description of the musical
     * @param basePrice      The base ticket price
     * @param durationMinutes The duration of the musical in minutes
     */
    public Musical(int id, String title, String description, double basePrice, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.basePrice = basePrice;
        this.durationMinutes = durationMinutes;
    }
} 