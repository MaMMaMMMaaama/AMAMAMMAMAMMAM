package org.example.recources;

import org.example.recources.generator.IdGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Worker implements Serializable {
    private static final long serialVersionUID = 78L;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int salary; //Значение поля должно быть больше 0
    private Position position; //Поле не может быть null
    private Status status; //Поле может быть null
    private Person person; //Поле не может быть null

    public Worker(Integer id, String name, Coordinates coordinates, Date creationDate, int salary, Position position, Status status, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.person = person;
    }

    public Worker(){
        this.id = IdGenerator.generateId();
        this.creationDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinate X=" + coordinates.getX() +
                ", coordinate Y=" + coordinates.getY() +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", position=" + position +
                ", status=" + status +
                ", person=" + person +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

