package models;

import javax.persistence.*;

@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @SequenceGenerator(name = "airplane_seq", sequenceName = "airplane_id ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airplane_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "max_seats")
    private int maxSeats;

    @Column(name = "flight_lenght")
    private int flightLenght;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    public Airplane() {}

    public Airplane(String model, int maxSeats, int flightLenght, int yearOfRelease){
        this.model = model;
        this.maxSeats = maxSeats;
        this.flightLenght = flightLenght;
        this.yearOfRelease = yearOfRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getFlightLenght() {
        return flightLenght;
    }

    public void setFlightLenght(int flightLenght) {
        this.flightLenght = flightLenght;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString(){
        return "airplane { " +
                "id = " + id +
                ", model = '" + model + '\'' +
                ", max_seats = '" + maxSeats + '\'' +
                ", flight_lenght = '" + flightLenght + '\'' +
                ", year_of_release = '" + yearOfRelease + '\'' +
                '}';
    }
}
