package ru.job4j.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String number;
    private Timestamp created;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Person> accounts;

    public static Employee of(int id, String name, String surname, String number, List<Person> accounts) {
        Employee em = new Employee();
        em.name = name;
        em.surname = surname;
        em.id = id;
        em.number = number;
        em.accounts = accounts;
        em.created = new Timestamp(System.currentTimeMillis());
        return em;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public List<Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Person> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(number, employee.number) && Objects.equals(created, employee.created) && Objects.equals(accounts, employee.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, number, created, accounts);
    }

    @Override
    public String toString() {
        return "Employee{"
               + "id=" + id
               + ", name='" + name + '\''
               + ", surname='" + surname + '\''
               + ", number='" + number + '\''
               + ", created=" + created
               + '}';
    }
}
