package br.com.taskmate.model.user;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // This annotation is used to generate a unique identifier for the user
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id; // UUID is a class that generates unique identifiers

    @Column(name = "firstName", updatable = true, nullable = false, length = 100)
    private String firstName;

    @Column(name = "lastName", updatable = true, nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", updatable = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", updatable = true, nullable = false, length = 100)
    private String password;

    @Column(name = "age", updatable = true, nullable = false)
    private Integer age;

    @Column(name = "phone", updatable = true, nullable = false)
    private String phone;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(UUID id, String firstName, String lastName, String email, String password, Integer age, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone = phone;
    }
}
