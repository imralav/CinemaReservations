package pl.com.imralav.vxml.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @JoinTable(name="booking_seats")
    private List<Seat> seat;

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



    public void setShowing(Showing showing) {
        this.showing = showing;
    }



    public List<Seat> getSeat() {
        return seat;
    }



    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }


    @Override
    public String toString() {
        return "Booking [id=" + id + ", customer=" + customer + ", seat=" + seat + ", showing=" + showing + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((seat == null) ? 0 : seat.hashCode());
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
        if (seat == null) {
            if (other.seat != null) {
                return false;
            }
        } else if (!seat.equals(other.seat)) {
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
