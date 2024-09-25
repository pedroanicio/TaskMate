package br.com.taskmate.model.user;

import br.com.taskmate.model.user.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // This annotation is used to generate a unique identifier for the user
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id; // UUID is a class that generates unique identifiers

    @Column(name = "username", updatable = true, nullable = false, length = 100)
    private String username;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", updatable = true)
    private UserRole role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserRole getRole() { return role; }

    public void setRole(UserRole role) { this.role = role; }

    public User(UUID id, String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.role = role;
    }

    public User(String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.role = role;
    }

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CLIENT"), new SimpleGrantedAuthority("ROLE_WORKER"));
        else if (this.role == UserRole.WORKER) return List.of(new SimpleGrantedAuthority("ROLE_WORKER"), new SimpleGrantedAuthority("ROLE_CLIENT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }


}
