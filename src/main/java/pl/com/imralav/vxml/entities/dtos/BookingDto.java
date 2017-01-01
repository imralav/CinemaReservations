package pl.com.imralav.vxml.entities.dtos;

public class BookingDto {

    private String movieTitle;
    private String readableDate;
    private String readableTime;
    private String readableSeats;
    private Integer showingId;

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


    public Integer getShowingId() {
        return showingId;
    }


    public void setShowingId(Integer showingId) {
        this.showingId = showingId;
    }

    @Override
    public String toString() {
        return "BookingDto [movieTitle=" + movieTitle + ", readableDate=" + readableDate + ", readableTime="
                + readableTime + ", readableSeats=" + readableSeats + ", showingId=" + showingId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movieTitle == null) ? 0 : movieTitle.hashCode());
        result = prime * result + ((readableDate == null) ? 0 : readableDate.hashCode());
        result = prime * result + ((readableSeats == null) ? 0 : readableSeats.hashCode());
        result = prime * result + ((readableTime == null) ? 0 : readableTime.hashCode());
        result = prime * result + ((showingId == null) ? 0 : showingId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BookingDto other = (BookingDto)obj;
        if (movieTitle == null) {
            if (other.movieTitle != null) {
                return false;
            }
        } else if (!movieTitle.equals(other.movieTitle)) {
            return false;
        }
        if (readableDate == null) {
            if (other.readableDate != null) {
                return false;
            }
        } else if (!readableDate.equals(other.readableDate)) {
            return false;
        }
        if (readableSeats == null) {
            if (other.readableSeats != null) {
                return false;
            }
        } else if (!readableSeats.equals(other.readableSeats)) {
            return false;
        }
        if (readableTime == null) {
            if (other.readableTime != null) {
                return false;
            }
        } else if (!readableTime.equals(other.readableTime)) {
            return false;
        }
        if (showingId == null) {
            if (other.showingId != null) {
                return false;
            }
        } else if (!showingId.equals(other.showingId)) {
            return false;
        }
        return true;
    }
}
