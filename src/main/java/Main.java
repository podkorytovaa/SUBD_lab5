import logics.*;
import org.hibernate.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HibernateSessionFactory.buildSessionFactory();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        boolean work = true;
        while (work) {
            try {
                System.out.println("\n\n----------------- ГЛАВНОЕ МЕНЮ -----------------" +
                        "\n1 - самолет" +
                        "\n2 - капитан воздушного судна" +
                        "\n3 - рейс" +
                        "\n4 - ценообразование" +
                        "\n5 - маршрут" +
                        "\n6 - запрос из 2 лабораторной работы" +
                        "\n7 - завершение работы");
                System.out.print("Введите цифру, соответствующую нужному пункту: ");
                Scanner scanner = new Scanner(System.in);
                int index = scanner.nextInt();
                switch (index) {
                    case 1:
                        AirplaneLogic airplaneLogic = new AirplaneLogic();
                        airplaneLogic.menuAirplane(session);
                        break;
                    case 2:
                        AirplaneCommanderLogic commanderLogic = new AirplaneCommanderLogic();
                        commanderLogic.menuAirplaneCommander(session);
                        break;
                    case 3:
                        FlightLogic orderLogic = new FlightLogic();
                        orderLogic.menuFlight(session);
                        break;
                    case 4:
                        PricingLogic productLogic = new PricingLogic();
                        productLogic.menuPricing(session);
                        break;
                    case 5:
                        RouteLogic routeLogic = new RouteLogic();
                        routeLogic.menuRoute(session);
                        break;
                    case 6:
                        SQLRequestLab2Logic request = new SQLRequestLab2Logic();
                        request.work(session);
                        break;
                    case 7:
                        work = false;
                        break;
                }
            }
            catch (Exception ex) {
                System.out.println("Ошибка: " + ex + ". Проверьте данные и попробуйте еще раз.");
            }
        }
        session.close();
    }
}
