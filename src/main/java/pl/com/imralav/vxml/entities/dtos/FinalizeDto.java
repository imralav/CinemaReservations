package pl.com.imralav.vxml.entities.dtos;

import java.util.List;

import pl.com.imralav.vxml.entities.Seat;

public class FinalizeDto {
    private Integer showingId;
    private List<Seat> seats;

    public FinalizeDto(Integer showingId, List<Seat> seats) {
        this.showingId = showingId;
        this.seats = seats;
    }

    public Integer getShowingId() {
        return showingId;
    }

    public void setShowingId(Integer showingId) {
        this.showingId = showingId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "FinalizeDto [showingId=" + showingId + ", seats=" + seats + "]";
    }
}
