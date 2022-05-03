package models;

import javax.persistence.*;

@Entity
@Table(name = "pricing")
public class Pricing {
    @Id
    @SequenceGenerator(name = "pricing_seq", sequenceName = "pricing_id ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricing_seq")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "routeid")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "airplaneid")
    private Airplane airplane;

    @Column(name = "ticket_price")
    private int ticketPrice;

    public Pricing() {}

    public Pricing(Route route, Airplane airplane, int ticketPrice){
        this.route = route;
        this.airplane = airplane;
        this.ticketPrice = ticketPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString(){
        return "route { " +
                "id = " + id +
                ", route = '" + route.getId() + '\'' +
                ", airplane = '" + airplane.getId() + '\'' +
                ", ticket_price = '" + ticketPrice + '\'' +
                '}';
    }
}
