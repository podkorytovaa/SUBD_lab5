package logics;

import models.Flight;
import org.hibernate.Session;

import java.util.List;

public class SQLRequestLab2Logic {
    public void work(Session s) {
        Session session = s.getSession();
        session.beginTransaction();

        List<Flight> flights = session.createQuery("SELECT a FROM Flight a", Flight.class).getResultList();
        System.out.println("\n---------------------------РАСПИСАНИЕ РЕЙСОВ----------------------------");
        System.out.println("   Дата\t\t|\t  Время\t\t|  Аэропорт отправления\t|\tМесто прибытия");
        System.out.print("------------------------------------------------------------------------");
        for (int i = 0; i < flights.size(); i++) {
            System.out.format("\n%s\t|\t%s\t|\t%s\t\t|\t%s", flights.get(i).getDepartureDate().toString(),
                    flights.get(i).getDepartureTime().toString(), flights.get(i).getPricing().getRoute().getDepartureAirport(), flights.get(i).getPricing().getRoute().getArrivalCity());
        }
        session.getTransaction().commit();
    }
}
