package pl.com.imralav.vxml.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "booking_seats", inverseJoinColumns=@JoinColumn(name="SEAT_ID", referencedColumnName="ID"))
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne
    private Showing showing;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Showing getShowing() {
        return showing;
    }

    public LocalDate getShowingDate() {
        return showing.getShowingDatetime().toLocalDate();
    }

    public LocalTime getShowingTime() {
        return showing.getShowingDatetime().toLocalTime();
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public String getMovieTitle() {
        return showing.getMovieTitle();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }


    @Override
    public String toString() {
        return "Booking [id=" + id + ", customer=" + customer + ", seats=" + seats + ", showing=" + showing + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((seats == null) ? 0 : seats.hashCode());
        result = prime * result + ((showing == null) ? 0 : showing.hashCode());
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
        Booking other = (Booking)obj;
        if (customer == null) {
            if (other.customer != null) {
                return false;
            }
        } else if (!customer.equals(other.customer)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (seats == null) {
            if (other.seats != null) {
                return false;
            }
        } else if (!seats.equals(other.seats)) {
            return false;
        }
        if (showing == null) {
            if (other.showing != null) {
                return false;
            }
        } else if (!showing.equals(other.showing)) {
            return false;
        }
        return true;
    }

}
