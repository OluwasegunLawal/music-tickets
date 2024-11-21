package musicals.models;
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

    public Musical getMusical() {
        return musical;
    }

    public void setMusical(Musical musical) {
        this.musical = musical;
    }
}