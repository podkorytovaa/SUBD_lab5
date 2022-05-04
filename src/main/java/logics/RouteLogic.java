package logics;

import models.Route;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class RouteLogic {
    public void menuRoute(Session s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- МАРШРУТ -------------------" +
                "\n1 - добавление маршрута" +
                "\n2 - изменение маршрута" +
                "\n3 - удаление маршрута" +
                "\n4 - список маршрутов" +
                "\n5 - фильтрация");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        Session session = s.getSession();
        session.beginTransaction();
        switch (index) {
            case 1:
                addRoute(session);
                break;
            case 2:
                updateRoute(session);
                break;
            case 3:
                deleteRoute(session);
                break;
            case 4:
                readRoute(session);
                break;
            case 5:
                filterRoute(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void addRoute(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите город отправления: ");
        String departureCity = scanner.next();
        System.out.print("Введите аэропорт отправления: ");
        scanner = new Scanner(System.in);
        String departureAirport = scanner.nextLine();
        System.out.print("Введите город прибытия: ");
        String arrivalCity = scanner.next();
        System.out.print("Введите аэропорт прибытия: ");
        scanner = new Scanner(System.in);
        String arrivalAirport = scanner.nextLine();
        System.out.print("Введите расстояние между городами: ");
        int distance = scanner.nextInt();
        Route route = new Route(departureCity, departureAirport, arrivalCity, arrivalAirport, distance);
        session.save(route);
    }

    private void readRoute(Session session) {
        List<Route> routes = session.createQuery("SELECT a from Route a", Route.class).getResultList();
        System.out.println(routes);
    }

    private void updateRoute(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID маршрута: ");
        int id = scanner.nextInt();
        Route route = session.get(Route.class, id);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - город отправления" +
                "\n2 - аэропорт отправления" +
                "\n3 - город прибытия" +
                "\n4 - аэропорт прибытия" +
                "\n5 - расстояние между городами");
        System.out.print("Для изменения нужного поля введите соответствующую ему цифру: ");
        int index = scanner.nextInt();
        switch (index) {
            case 1:
                System.out.print("Введите город отправления: ");
                String departureCity = scanner.next();
                route.setDepartureCity(departureCity);
                session.save(route);
                break;
            case 2:
                System.out.print("Введите аэропорт отправления: ");
                scanner = new Scanner(System.in);
                String departureAirport = scanner.nextLine();
                route.setDepartureAirport(departureAirport);
                session.save(route);
                break;
            case 3:
                System.out.print("Введите город прибытия: ");
                String arrivalCity = scanner.next();
                route.setArrivalCity(arrivalCity);
                session.save(route);
                break;
            case 4:
                System.out.print("Введите аэропорт прибытия: ");
                scanner = new Scanner(System.in);
                String arrivalAirport = scanner.nextLine();
                route.setArrivalAirport(arrivalAirport);
                session.save(route);
                break;
            case 5:
                System.out.print("Введите расстояние между городами: ");
                int distance = scanner.nextInt();
                route.setDistance(distance);
                session.save(route);
                break;
        }
    }

    private void deleteRoute(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите ID маршрута: ");
        int id = scanner.nextInt();
        Route route = session.get(Route.class, id);
        session.delete(route);
    }

    private void filterRoute(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - фильтрация по городу отправления" +
                "\n2 - фильтрация по аэропорту отправления" +
                "\n3 - фильтрация по городу прибытия" +
                "\n4 - фильтрация по аэропорту прибытия" +
                "\n5 - фильтрация по расстоянию между городами");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        List<Route> routes = null;
        switch(index) {
            case 1:
                System.out.print("Введите город отправления: ");
                String departureCity = scanner.next();
                routes = session.createQuery("SELECT a from Route a where departure_city = \'" + departureCity + "\'", Route.class).getResultList();
                break;
            case 2:
                System.out.print("Введите аэропорт отправления: ");
                scanner = new Scanner(System.in);
                String departureAirport = scanner.nextLine();
                routes = session.createQuery("SELECT a from Route a where departure_airport = \'" + departureAirport + "\'", Route.class).getResultList();
                break;
            case 3:
                System.out.print("Введите город прибытия: ");
                String arrivalCity = scanner.next();
                routes = session.createQuery("SELECT a from Route a where arrival_city = \'" + arrivalCity + "\'", Route.class).getResultList();
                break;
            case 4:
                System.out.print("Введите аэропорт прибытия: ");
                scanner = new Scanner(System.in);
                String arrivalAirport = scanner.nextLine();
                routes = session.createQuery("SELECT a from Route a where arrival_airport = \'" + arrivalAirport + "\'", Route.class).getResultList();
                break;
            case 5:
                System.out.print("Введите расстояние между городами: ");
                int distance = scanner.nextInt();
                routes = session.createQuery("SELECT a from Route a where distance = \'" + distance + "\'", Route.class).getResultList();
                break;
        }
        System.out.println(routes);
    }
}
