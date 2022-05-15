package logics;

import models.Airplane;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class AirplaneLogic {
    public void menuAirplane(Session s) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n------------------- САМОЛЕТ -------------------" +
                    "\n1 - добавление самолета" +
                    "\n2 - изменение самолета" +
                    "\n3 - удаление самолета" +
                    "\n4 - список самолетов" +
                    "\n5 - фильтрация");
            System.out.print("Введите цифру, соответствующую нужному пункту: ");
            int index = scanner.nextInt();
            Session session = s.getSession();
            session.beginTransaction();
            switch (index) {
                case 1:
                    addAirplane(session);
                    break;
                case 2:
                    updateAirplane(session);
                    break;
                case 3:
                    deleteAirplane(session);
                    break;
                case 4:
                    readAirplane(session);
                    break;
                case 5:
                    filterAirplane(session);
                    break;
            }
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            System.out.println("Ошибка: " + ex + ". Проверьте данные и попробуйте еще раз.");
        }
    }

    private void addAirplane(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите модель самолета: ");
            String model = scanner.nextLine();
            System.out.print("Введите максимальное количество мест в самолете: ");
            int maxSeats = scanner.nextInt();
            System.out.print("Введите максимальную длину полета самолета: ");
            int flightLenght = scanner.nextInt();
            System.out.print("Введите год выпуска самолета: ");
            int yearOfRelease = scanner.nextInt();
            Airplane airplane = new Airplane(model, maxSeats, flightLenght, yearOfRelease);
            session.save(airplane);
        }
        catch (Exception ex) {
            System.out.println("Ошибка: " + ex + ". Проверьте данные и попробуйте еще раз.");
        }
    }

    private void readAirplane(Session session) {
        List<Airplane> airplanes = session.createQuery("SELECT a from Airplane a", Airplane.class).getResultList();
        System.out.println(airplanes);
    }

    private void updateAirplane(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите ID самолета: ");
            int id = scanner.nextInt();
            Airplane airplane = session.get(Airplane.class, id);
            System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                    "\n1 - модель самолета" +
                    "\n2 - максимальное количество мест в самолете" +
                    "\n3 - максимальная длина полета самолета" +
                    "\n4 - год выпуска самолета");
            System.out.print("Для изменения нужного поля введите соответствующую ему цифру: ");
            int index = scanner.nextInt();
            switch (index) {
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.print("Введите модель самолета: ");
                    String model = scanner.nextLine();
                    airplane.setModel(model);
                    session.save(airplane);
                    break;
                case 2:
                    System.out.print("Введите максимальное количество мест в самолете: ");
                    int maxSeats = scanner.nextInt();
                    airplane.setMaxSeats(maxSeats);
                    session.save(airplane);
                    break;
                case 3:
                    System.out.print("Введите максимальную длину полета самолета: ");
                    int flightLenght = scanner.nextInt();
                    airplane.setFlightLenght(flightLenght);
                    session.save(airplane);
                    break;
                case 4:
                    System.out.print("Введите год выпуска самолета: ");
                    int yearOfRelease = scanner.nextInt();
                    airplane.setYearOfRelease(yearOfRelease);
                    session.save(airplane);
                    break;
            }
        }
        catch (Exception ex) {
            System.out.println("Ошибка: " + ex + ". Проверьте данные и попробуйте еще раз.");
        }
    }

    private void deleteAirplane(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nВведите ID самолета: ");
            int id = scanner.nextInt();
            Airplane airplane = session.get(Airplane.class, id);
            session.delete(airplane);
        }
        catch (Exception ex) {
            System.out.println("Ошибка: " + ex + ". Проверьте данные и попробуйте еще раз.");
        }
    }

    private void filterAirplane(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                    "\n1 - фильтрация по модели самолета" +
                    "\n2 - фильтрация по максимальному количеству мест в самолете" +
                    "\n3 - фильтрация по максимальной длине полета самолета" +
                    "\n4 - фильтрация по году выпуска самолета");
            System.out.print("Введите цифру, соответствующую нужному пункту: ");
            int index = scanner.nextInt();
            List<Airplane> airplanes = null;
            switch (index) {
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.print("Введите модель самолета: ");
                    String model = scanner.nextLine();
                    airplanes = session.createQuery("SELECT a from Airplane a where model = \'" + model + "\'", Airplane.class).getResultList();
                    break;
                case 2:
                    System.out.print("Введите максимальное количество мест в самолете: ");
                    int maxSeats = scanner.nextInt();
                    airplanes = session.createQuery("SELECT a from Airplane a where max_seats = \'" + maxSeats + "\'", Airplane.class).getResultList();
                    break;
                case 3:
                    System.out.print("Введите максимальную длину полета самолета: ");
                    int flightLenght = scanner.nextInt();
                    airplanes = session.createQuery("SELECT a from Airplane a where flight_lenght = \'" + flightLenght + "\'", Airplane.class).getResultList();
                    break;
                case 4:
                    System.out.print("Введите год выпуска самолета: ");
                    int yearOfRelease = scanner.nextInt();
                    airplanes = session.createQuery("SELECT a from Airplane a where year_of_release = \'" + yearOfRelease + "\'", Airplane.class).getResultList();
                    break;
            }
            System.out.println(airplanes);
        }
        catch (Exception ex) {
            System.out.println("Ошибка: " + ex + ". Проверьте данные и попробуйте еще раз.");
        }
    }
}
