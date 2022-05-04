package logics;

import models.*;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class FlightLogic {
    public void menuFlight(Session s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--------------------- РЕЙС ---------------------" +
                "\n1 - добавление рейса" +
                "\n2 - изменение рейса" +
                "\n3 - удаление рейса" +
                "\n4 - список рейсов" +
                "\n5 - фильтрация");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        Session session = s.getSession();
        session.beginTransaction();
        switch (index) {
            case 1:
                addFlight(session);
                break;
            case 2:
                updateFlight(session);
                break;
            case 3:
                deleteFlight(session);
                break;
            case 4:
                readFlight(session);
                break;
            case 5:
                filterFlight(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void addFlight(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите день недели рейса: ");
        String dayOfWeek = scanner.next();
        System.out.print("Введите дату отправления (в формате год-мясяц-день): ");
        String departureDateString = scanner.next();
        Date departureDate = Date.valueOf(departureDateString);
        System.out.print("Введите время отправления (в формате часы:минуты:секунды): ");
        String departureTimeString = scanner.next();
        Time departureTime = Time.valueOf(departureTimeString);
        System.out.print("Введите время прибытия (в формате часы:минуты:секунды): ");
        String arrivalTimeString = scanner.next();
        Time arrivalTime = Time.valueOf(arrivalTimeString);
        System.out.print("Введите номер командира ВС: ");
        int commanderId = scanner.nextInt();
        System.out.print("Введите номер ценообразования: ");
        int pricingId = scanner.nextInt();
        System.out.print("Введите количество занятых мест: ");
        int numberOfOccupiedPlaces = scanner.nextInt();
        Flight flight = new Flight(dayOfWeek, departureDate, departureTime, arrivalTime, session.get(Airplane_Commander.class, commanderId), session.get(Pricing.class, pricingId), numberOfOccupiedPlaces);
        session.save(flight);
    }

    private void readFlight(Session session) {
        List<Flight> flights = session.createQuery("SELECT a from Flight a", Flight.class).getResultList();
        System.out.println(flights);
    }

    private void updateFlight(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID рейса: ");
        int id = scanner.nextInt();
        Flight flight = session.get(Flight.class, id);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - день недели рейса" +
                "\n2 - дата отправления" +
                "\n3 - время отправления" +
                "\n4 - время прибытия" +
                "\n5 - номер командира ВС" +
                "\n6 - номер ценообразования" +
                "\n7 - количество занятых мест");
        System.out.print("Для изменения нужного поля введите соответствующую ему цифру: ");
        int index = scanner.nextInt();
        switch (index) {
            case 1:
                System.out.print("Введите день недели рейса: ");
                String dayOfWeek = scanner.next();
                flight.setDayOfWeek(dayOfWeek);
                session.save(flight);
                break;
            case 2:
                System.out.print("Введите дату отправления (в формате год-мясяц-день): ");
                String departureDateString = scanner.next();
                Date departureDate = Date.valueOf(departureDateString);
                flight.setDepartureDate(departureDate);
                session.save(flight);
                break;
            case 3:
                System.out.print("Введите время отправления (в формате часы:минуты:секунды): ");
                String departureTimeString = scanner.next();
                Time departureTime = Time.valueOf(departureTimeString);
                flight.setDepartureTime(departureTime);
                session.save(flight);
                break;
            case 4:
                System.out.print("Введите время прибытия (в формате часы:минуты:секунды): ");
                String arrivalTimeString = scanner.next();
                Time arrivalTime = Time.valueOf(arrivalTimeString);
                flight.setArrivalTime(arrivalTime);
                session.save(flight);
                break;
            case 5:
                System.out.print("Введите номер командира ВС: ");
                int commanderId = scanner.nextInt();
                flight.setCommander(session.get(Airplane_Commander.class, commanderId));
                session.save(flight);
                break;
            case 6:
                System.out.print("Введите номер ценообразования: ");
                int pricingId = scanner.nextInt();
                flight.setPricing(session.get(Pricing.class, pricingId));
                session.save(flight);
                break;
            case 7:
                System.out.print("Введите количество занятых мест: ");
                int numberOfOccupiedPlaces = scanner.nextInt();
                flight.setNumberOfOccupiedPlaces(numberOfOccupiedPlaces);
                session.save(flight);
                break;
        }
    }

    private void deleteFlight(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите ID рейса: ");
        int id = scanner.nextInt();
        Flight flights = session.get(Flight.class, id);
        session.delete(flights);
        System.out.print("\nЗапись с ID = " + id + " удалена.");
    }

    private void filterFlight(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - фильтрация по дню недели" +
                "\n2 - фильтрация по дате отправления" +
                "\n3 - фильтрация по времени отправления" +
                "\n4 - фильтрация по времени прибытия" +
                "\n5 - фильтрация по номеру командира ВС" +
                "\n6 - фильтрация по номеру ценообразования" +
                "\n7 - фильтрация по количеству занятых мест");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        List<Flight> flights = null;
        switch(index) {
            case 1:
                System.out.print("Введите день недели рейса: ");
                String dayOfWeek = scanner.next();
                flights = session.createQuery("SELECT a from Flight a where day_of_week = \'" + dayOfWeek + "\'", Flight.class).getResultList();
                break;
            case 2:
                System.out.print("Введите дату отправления (в формате год-мясяц-день): ");
                String departureDateString = scanner.next();
                Date departureDate = Date.valueOf(departureDateString);
                flights = session.createQuery("SELECT a from Flight a where departure_date = \'" + departureDate + "\'", Flight.class).getResultList();
                break;
            case 3:
                System.out.print("Введите время отправления (в формате часы:минуты:секунды): ");
                String departureTimeString = scanner.next();
                Time departureTime = Time.valueOf(departureTimeString);
                flights = session.createQuery("SELECT a from Flight a where departure_time = \'" + departureTime + "\'", Flight.class).getResultList();
                break;
            case 4:
                System.out.print("Введите время прибытия (в формате часы:минуты:секунды): ");
                String arrivalTimeString = scanner.next();
                Time arrivalTime = Time.valueOf(arrivalTimeString);
                flights = session.createQuery("SELECT a from Flight a where arrival_time = \'" + arrivalTime + "\'", Flight.class).getResultList();
                break;
            case 5:
                System.out.print("Введите номер командира ВС: ");
                int commanderId = scanner.nextInt();
                flights = session.createQuery("SELECT a from Flight a where airplane_commanderid = \'" + commanderId + "\'", Flight.class).getResultList();
                break;
            case 6:
                System.out.print("Введите номер ценообразования: ");
                int pricingId = scanner.nextInt();
                flights = session.createQuery("SELECT a from Flight a where pricingid = \'" + pricingId + "\'", Flight.class).getResultList();
                break;
            case 7:
                System.out.print("Введите количество занятых мест: ");
                int numberOfOccupiedPlaces = scanner.nextInt();
                flights = session.createQuery("SELECT a from Flight a where number_of_occupied_places = \'" + numberOfOccupiedPlaces + "\'", Flight.class).getResultList();
                break;
        }
        System.out.println(flights);
    }
}
