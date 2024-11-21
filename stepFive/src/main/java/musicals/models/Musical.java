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

    // The path to the image file for the musical
    private String imagePath;
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
