import logics.*;
import org.hibernate.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HibernateSessionFactory.buildSessionFactory();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        boolean work = true;
        while(work){
            System.out.println("\n----------------- ГЛАВНОЕ МЕНЮ -----------------" +
                    "\n1 - самолет" +
                    "\n2 - капитан воздушного судна" +
                    "\n3 - рейс" +
                    "\n4 - ценообразование" +
                    "\n5 - маршрут" +
                    "\n6 - завершение работы");
            System.out.print("Введите цифру, соответствующую нужному пункту: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            switch (index){
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
                    work = false;
                    break;
            }
        }
        session.close();
    }
}
