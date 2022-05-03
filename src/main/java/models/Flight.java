package models;

import javax.persistence.*;
import java.sql.*;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @SequenceGenerator(name = "flight_seq", sequenceName = "flight_id ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "departure_time")
    private Time departureTime;

    @Column(name = "arrival_time")
    private Time arrivalTime;

    @ManyToOne
    @JoinColumn(name = "airplane_commanderid")
    private Airplane_Commander commander;

    @ManyToOne
    @JoinColumn(name = "pricingid")
    private Pricing pricing;

    @Column(name = "number_of_occupied_places")
    private int numberOfOccupiedPlaces;

    public Flight() {}

    public Flight(String dayOfWeek, Date departureDate, Time departureTime, Time arrivalTime, Airplane_Commander commander, Pricing pricing, int numberOfOccupiedPlaces) {
        this.dayOfWeek = dayOfWeek;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.commander = commander;
        this.pricing = pricing;
        this.numberOfOccupiedPlaces = numberOfOccupiedPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airplane_Commander getCommander() {
        return commander;
    }

    public void setCommander(Airplane_Commander commander) {
        this.commander = commander;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public int getNumberOfOccupiedPlaces() {
        return numberOfOccupiedPlaces;
    }

    public void setNumberOfOccupiedPlaces(int numberOfOccupiedPlaces) {
        this.numberOfOccupiedPlaces = numberOfOccupiedPlaces;
    }

    @Override
    public String toString(){
        return "route { " +
                "id = " + id +
                ", day_of_week = '" + dayOfWeek + '\'' +
                ", departure_date = '" + departureDate + '\'' +
                ", departure_time = '" + departureTime + '\'' +
                ", arrival_time = '" + arrivalTime + '\'' +
                ", commander = '" + commander.getId() + '\'' +
                ", pricing = '" + pricing.getId() + '\'' +
                ", number_of_occupied_places = '" + numberOfOccupiedPlaces + '\'' +
                '}';
    }
}
