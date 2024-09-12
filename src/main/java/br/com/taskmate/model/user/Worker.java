package br.com.taskmate.model.user;

import br.com.taskmate.model.Service;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workers")
public class Worker extends User{

    @Column(name = "profession", updatable = true, nullable = false, length = 100)
    private String profession;

    @Column(name = "description", updatable = true, nullable = false, length = 100)
    private String description;

    @Column(name = "rating", updatable = true, nullable = false)
    private Double rating;

    private List<Service> services;

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Worker(UUID id, String firstName, String lastName, String email, String password, Integer age, String phone, String profession, String description, Double rating, List<Service> services) {
        super(id, firstName, lastName, email, password, age, phone);
        this.profession = profession;
        this.description = description;
        this.rating = rating;
        this.services = services;
    }

}
