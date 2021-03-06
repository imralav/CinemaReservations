package pl.com.imralav.vxml.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer seatNumber;

    private Integer rowNumber;

    public Seat() {}

    public Seat(int seatNumber, int rowNumber) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getSeatNumber() {
        return seatNumber;
    }


    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }


    public Integer getRowNumber() {
        return rowNumber;
    }


    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }


    @Override
    public String toString() {
        return "Seat [id=" + id + ", seatNumber=" + seatNumber + ", rowNumber=" + rowNumber + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((rowNumber == null) ? 0 : rowNumber.hashCode());
        result = prime * result + ((seatNumber == null) ? 0 : seatNumber.hashCode());
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
        Seat other = (Seat)obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (rowNumber == null) {
            if (other.rowNumber != null) {
                return false;
            }
        } else if (!rowNumber.equals(other.rowNumber)) {
            return false;
        }
        if (seatNumber == null) {
            if (other.seatNumber != null) {
                return false;
            }
        } else if (!seatNumber.equals(other.seatNumber)) {
            return false;
        }
        return true;
    }

}
