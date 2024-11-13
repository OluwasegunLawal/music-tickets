package musicals.models;
import java.time.LocalDateTime;

/**
 * Represents a scheduled showing of a musical performance with specific date, time, and seating information.
 */
public class ShowTime {
    /** Unique identifier for the show time */
    private int id;
    
    /** The musical being performed at this show time */
    private Musical musical;
    
    /** Date and time when the show is scheduled */
    private LocalDateTime dateTime;
    
    /** Number of seats still available for purchase */
    private int availableSeats;
    
    /**
     * Creates a new ShowTime instance
     * @param id Unique identifier for the show time
     * @param musical The musical being performed
     * @param dateTime The date and time of the performance
     * @param availableSeats Number of seats available for purchase
     */
    public ShowTime(int id, Musical musical, LocalDateTime dateTime, int availableSeats) {
        this.id = id;
        this.musical = musical;
        this.dateTime = dateTime;
        this.availableSeats = availableSeats;
    }
} 