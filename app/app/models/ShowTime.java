package app.models;
import java.time.LocalDateTime;

public class ShowTime {
    private int id;
    private Musical musical;
    private LocalDateTime dateTime;
    private int availableSeats;
    
    public ShowTime(int id, Musical musical, LocalDateTime dateTime, int availableSeats) {
        this.id = id;
        this.musical = musical;
        this.dateTime = dateTime;
        this.availableSeats = availableSeats;
    }
} 