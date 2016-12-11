package pl.com.imralav.vxml.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHOWING")
public class Showing {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Auditorium auditorium;


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


    public Auditorium getAuditorium() {
        return auditorium;
    }


    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }


    @Override
    public String toString() {
        return "Showing [id=" + id + ", movie=" + movie + ", auditorium=" + auditorium + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auditorium == null) ? 0 : auditorium.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
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
        if (auditorium == null) {
            if (other.auditorium != null) {
                return false;
            }
        } else if (!auditorium.equals(other.auditorium)) {
            return false;
        }
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
        return true;
    }
}
