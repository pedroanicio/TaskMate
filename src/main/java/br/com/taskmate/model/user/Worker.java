package br.com.taskmate.model.user;

import br.com.taskmate.model.Work;
import br.com.taskmate.model.user.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "worker")
    private List<Work> works;

    public void addWork(Work work) {
        works.add(work);
    }

    public void removeWork(Work work) {
        works.remove(work);
    }

    public List<Work> getServices() {
        return works;
    }

    public void setServices(List<Work> services) {
        this.works = services;
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


    public Worker(UUID id, String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role, String profession, String description, Double rating, List<Work> works) {
        super(id, username, firstName, lastName, email, password, age, phone, role);
        this.profession = profession;
        this.description = description;
        this.rating = rating;
        this.works = works;
    }

    public Worker() {
    }


}
