package pl.com.imralav.vxml.entities.dtos;

public class BookingDto {

    private String movieTitle;
    private String readableDate;
    private String readableTime;
    private String readableSeats;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getReadableDate() {
        return readableDate;
    }

    public void setReadableDate(String readableDate) {
        this.readableDate = readableDate;
    }

    public String getReadableTime() {
        return readableTime;
    }

    public void setReadableTime(String readableTime) {
        this.readableTime = readableTime;
    }

    public String getReadableSeats() {
        return readableSeats;
    }

    public void setReadableSeats(String readableSeats) {
        this.readableSeats = readableSeats;
    }

    @Override
    public String toString() {
        return "BookingDto [movieTitle=" + movieTitle + ", readableDate=" + readableDate + ", readableTime="
                + readableTime + ", readableSeats=" + readableSeats + "]";
    }

}
