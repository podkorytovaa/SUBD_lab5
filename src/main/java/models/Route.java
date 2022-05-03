package models;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @SequenceGenerator(name = "route_seq", sequenceName = "route_id ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Column(name = "distance")
    private int distance;

    public Route() {}

    public Route(String departureCity, String departureAirport, String arrivalCity, String arrivalAirport, int distance){
        this.departureCity = departureCity;
        this.departureAirport = departureAirport;
        this.arrivalCity = arrivalCity;
        this.arrivalAirport = arrivalAirport;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString(){
        return "airplane_commander { " +
                "id = " + id +
                ", departure_city = '" + departureCity + '\'' +
                ", departure_airport = '" + departureAirport + '\'' +
                ", arrival_city = '" + arrivalCity + '\'' +
                ", arrival_airport = '" + arrivalAirport + '\'' +
                ", distance = '" + distance + '\'' +
                '}';
    }
}
