package logics;

import models.*;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class PricingLogic {
    public void menuPricing(Session s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---------------- ЦЕНООБРАЗОВАНИЕ ----------------" +
                "\n1 - добавление ценообразования" +
                "\n2 - изменение ценообразования" +
                "\n3 - удаление ценообразования" +
                "\n4 - список ценообразования" +
                "\n5 - фильтрация");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        Session session = s.getSession();
        session.beginTransaction();
        switch (index) {
            case 1:
                addPricing(session);
                break;
            case 2:
                updatePricing(session);
                break;
            case 3:
                deletePricing(session);
                break;
            case 4:
                readPricing(session);
                break;
            case 5:
                filterPricing(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void addPricing(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер маршрута: ");
        int routeId = scanner.nextInt();
        System.out.print("Введите номер самолета: ");
        int airplaneId = scanner.nextInt();
        System.out.print("Введите цену билета: ");
        int ticketPrice = scanner.nextInt();
        Pricing pricing = new Pricing(session.get(Route.class, routeId), session.get(Airplane.class, airplaneId), ticketPrice);
        session.save(pricing);
    }

    private void readPricing(Session session) {
        List<Pricing> pricing = session.createQuery("SELECT a from Pricing a", Pricing.class).getResultList();
        System.out.println(pricing);
    }

    private void updatePricing(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID ценообразования: ");
        int id = scanner.nextInt();
        Pricing pricing = session.get(Pricing.class, id);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - номер маршрута" +
                "\n2 - номер самолета" +
                "\n3 - цена билета");
        System.out.print("Для изменения нужного поля введите соответствующую ему цифру: ");
        int index = scanner.nextInt();
        switch (index) {
            case 1:
                System.out.print("Введите номер маршрута: ");
                int routeId = scanner.nextInt();
                pricing.setRoute(session.get(Route.class, routeId));
                session.save(pricing);
                break;
            case 2:
                System.out.print("Введите номер самолета: ");
                int airplaneId = scanner.nextInt();
                pricing.setAirplane(session.get(Airplane.class, airplaneId));
                session.save(pricing);
                break;
            case 3:
                System.out.print("Введите цену билета: ");
                int ticketPrice = scanner.nextInt();
                pricing.setTicketPrice(ticketPrice);
                session.save(pricing);
                break;
        }
    }

    private void deletePricing(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите ID ценообразования: ");
        int id = scanner.nextInt();
        Pricing pricing = session.get(Pricing.class, id);
        session.delete(pricing);
    }

    private void filterPricing(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - фильтрация по номеру маршрута" +
                "\n2 - фильтрация по номеру самолета" +
                "\n3 - фильтрация по цене билета");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        List<Pricing> pricing = null;
        switch(index) {
            case 1:
                System.out.print("Введите номер маршрута: ");
                int routeId = scanner.nextInt();
                pricing = session.createQuery("SELECT a from Pricing a where routeid = \'" + routeId + "\'", Pricing.class).getResultList();
                break;
            case 2:
                System.out.print("Введите номер самолета: ");
                int airplaneId = scanner.nextInt();
                pricing = session.createQuery("SELECT a from Pricing a where airplaneid = \'" + airplaneId + "\'", Pricing.class).getResultList();
                break;
            case 3:
                System.out.print("Введите цену билета: ");
                int ticketPrice = scanner.nextInt();
                pricing = session.createQuery("SELECT a from Pricing a where ticket_price = \'" + ticketPrice + "\'", Pricing.class).getResultList();
                break;
        }
        System.out.println(pricing);
    }
}
