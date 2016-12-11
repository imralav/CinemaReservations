package pl.com.imralav.vxml.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer customerId;

    private Integer seatId;

    private Integer showingId;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public Integer getSeatId() {
        return seatId;
    }


    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }


    public Integer getShowingId() {
        return showingId;
    }


    public void setShowingId(Integer showingId) {
        this.showingId = showingId;
    }


    @Override
    public String toString() {
        return "Booking [id=" + id + ", customerId=" + customerId + ", seatId=" + seatId + ", showingId=" + showingId
                + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((seatId == null) ? 0 : seatId.hashCode());
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
        Booking other = (Booking)obj;
        if (customerId == null) {
            if (other.customerId != null) {
                return false;
            }
        } else if (!customerId.equals(other.customerId)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (seatId == null) {
            if (other.seatId != null) {
                return false;
            }
        } else if (!seatId.equals(other.seatId)) {
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
