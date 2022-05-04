package logics;

import models.Airplane_Commander;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AirplaneCommanderLogic {
    public void menuAirplaneCommander(Session s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------ КАПИТАН ВОЗДУШНОГО СУДНА -----------" +
                "\n1 - добавление командира ВС" +
                "\n2 - изменение командира ВС" +
                "\n3 - удаление командира ВС" +
                "\n4 - список командиров ВС" +
                "\n5 - фильтрация");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        Session session = s.getSession();
        session.beginTransaction();
        switch (index) {
            case 1:
                addAirplaneCommander(session);
                break;
            case 2:
                updateAirplaneCommander(session);
                break;
            case 3:
                deleteAirplaneCommander(session);
                break;
            case 4:
                readAirplaneCommander(session);
                break;
            case 5:
                filterAirplaneCommander(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void addAirplaneCommander(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ФИО командира ВС: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите номер телефона командира ВС: ");
        String phoneNumber = scanner.next();
        System.out.print("Введите дату рождения командира ВС (в формате год-мясяц-день): ");
        String dateOfBirthString = scanner.next();
        Date dateOfBirth = Date.valueOf(dateOfBirthString);
        System.out.print("Введите количество летных часов: ");
        int numberOfFlightHours = scanner.nextInt();
        Airplane_Commander сommander = new Airplane_Commander(fullName, phoneNumber, dateOfBirth, numberOfFlightHours);
        session.save(сommander);
    }

    private void readAirplaneCommander(Session session) {
        List<Airplane_Commander> commanders = session.createQuery("SELECT a from Airplane_Commander a", Airplane_Commander.class).getResultList();
        System.out.println(commanders);
    }

    private void updateAirplaneCommander(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID командира ВС: ");
        int id = scanner.nextInt();
        Airplane_Commander commander = session.get(Airplane_Commander.class, id);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - ФИО командира ВС" +
                "\n2 - номер телефона командира ВС" +
                "\n3 - дата рождения командира ВС" +
                "\n4 - количество летных часов");
        System.out.print("Для изменения нужного поля введите соответствующую ему цифру: ");
        int index = scanner.nextInt();
        switch (index) {
            case 1:
                scanner = new Scanner(System.in);
                System.out.print("Введите ФИО командира ВС: ");
                String fullName = scanner.nextLine();
                commander.setFullName(fullName);
                session.save(commander);
                break;
            case 2:
                System.out.print("Введите номер телефона командира ВС: ");
                String phoneNumber = scanner.next();
                commander.setPhoneNumber(phoneNumber);
                session.save(commander);
                break;
            case 3:
                System.out.print("Введите дату рождения командира ВС: ");
                String dateOfBirthString = scanner.next();
                Date dateOfBirth = Date.valueOf(dateOfBirthString);
                commander.setDateOfBirth(dateOfBirth);
                session.save(commander);
                break;
            case 4:
                System.out.print("Введите количество летных часов: ");
                int numberOfFlightHours = scanner.nextInt();
                commander.setNumberOfFlightHours(numberOfFlightHours);
                session.save(commander);
                break;
        }
    }

    private void deleteAirplaneCommander(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите ID командира ВС: ");
        int id = scanner.nextInt();
        Airplane_Commander commander = session.get(Airplane_Commander.class, id);
        session.delete(commander);
        System.out.print("\nЗапись с ID = " + id + " удалена.");
    }

    private void filterAirplaneCommander(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВОЗМОЖНЫЕ ВАРИАНТЫ" +
                "\n1 - фильтрация по ФИО командира ВС" +
                "\n2 - фильтрация по номеру телефона командира ВС" +
                "\n3 - фильтрация по дате рождения командира ВС" +
                "\n4 - фильтрация по количеству летных часов");
        System.out.print("Введите цифру, соответствующую нужному пункту: ");
        int index = scanner.nextInt();
        List<Airplane_Commander> commanders = null;
        switch(index) {
            case 1:
                scanner = new Scanner(System.in);
                System.out.print("Введите ФИО командира ВС: ");
                String fullName = scanner.nextLine();
                commanders = session.createQuery("SELECT a from Airplane_Commander a where full_name = \'" + fullName + "\'", Airplane_Commander.class).getResultList();
                break;
            case 2:
                System.out.print("Введите номер телефона командира ВС: ");
                String phoneNumber = scanner.next();
                commanders = session.createQuery("SELECT a from Airplane_Commander a where phone_number = \'" + phoneNumber + "\'", Airplane_Commander.class).getResultList();
                break;
            case 3:
                System.out.print("Введите дату рождения командира ВС: ");
                String dateOfBirthString = scanner.next();
                Date dateOfBirth = Date.valueOf(dateOfBirthString);
                commanders = session.createQuery("SELECT a from Airplane_Commander a where date_of_birth = \'" + dateOfBirth + "\'", Airplane_Commander.class).getResultList();
                break;
            case 4:
                System.out.print("Введите количество летных часов: ");
                int numberOfFlightHours = scanner.nextInt();
                commanders = session.createQuery("SELECT a from Airplane_Commander a where number_of_flight_hours = \'" + numberOfFlightHours + "\'", Airplane_Commander.class).getResultList();
                break;
        }
        System.out.println(commanders);
    }
}
