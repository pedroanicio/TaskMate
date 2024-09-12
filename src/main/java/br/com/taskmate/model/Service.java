package br.com.taskmate.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", updatable = true, nullable = false, length = 100)
    private String name;

    @Column(name = "description", updatable = true, nullable = false, length = 200)
    private String description;

    @Column(name = "price", updatable = true, nullable = false)
    private Double price; // per hour

    @Column(name = "location", updatable = true, nullable = false, length = 100)
    private String location;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Service(UUID id, String name, String description, Double price, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
    }
}
