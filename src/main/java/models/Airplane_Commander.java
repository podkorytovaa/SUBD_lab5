package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "airplane_commander")
public class Airplane_Commander {
    @Id
    @SequenceGenerator(name = "commander_seq", sequenceName = "commander_id ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commander_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "number_of_flight_hours")
    private int numberOfFlightHours;

    public Airplane_Commander() {}

    public Airplane_Commander(String fullName, String phoneNumber, Date dateOfBirth, int numberOfFlightHours){
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.numberOfFlightHours = numberOfFlightHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNumberOfFlightHours() {
        return numberOfFlightHours;
    }

    public void setNumberOfFlightHours(int numberOfFlightHours) {
        this.numberOfFlightHours = numberOfFlightHours;
    }

    @Override
    public String toString(){
        return "\n{ ID = " + id +
                ", ФИО = '" + fullName + '\'' +
                ", номер телефона = '" + phoneNumber + '\'' +
                ", дата рождения = '" + dateOfBirth + '\'' +
                ", количество летных часов = '" + numberOfFlightHours + '\'' +
                " }";
    }
}
