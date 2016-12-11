package pl.com.imralav.vxml.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Showing {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Movie movie;

    private LocalDateTime showingDatetime;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Movie getMovie() {
        return movie;
    }


    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getShowingDatetime() {
        return showingDatetime;
    }


    public void setShowingDatetime(LocalDateTime showingDatetime) {
        this.showingDatetime = showingDatetime;
    }


    @Override
    public String toString() {
        return "Showing [id=" + id + ", movie=" + movie + ", showingDatetime="
                + showingDatetime + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
        result = prime * result + ((showingDatetime == null) ? 0 : showingDatetime.hashCode());
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
        Showing other = (Showing)obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (movie == null) {
            if (other.movie != null) {
                return false;
            }
        } else if (!movie.equals(other.movie)) {
            return false;
        }
        if (showingDatetime == null) {
            if (other.showingDatetime != null) {
                return false;
            }
        } else if (!showingDatetime.equals(other.showingDatetime)) {
            return false;
        }
        return true;
    }
}
