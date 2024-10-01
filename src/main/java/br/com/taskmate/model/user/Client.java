package br.com.taskmate.model.user;

import br.com.taskmate.model.Work;
import br.com.taskmate.model.user.enums.UserRole;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client extends User{

    @ManyToMany
    @JoinTable(
            name = "client_work",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id")
    )
    private List<Work> contractedWorks;

    public List<Work> getContractedWorks() {
        return contractedWorks;
    }

    public void setContractedWorks(List<Work> cotractedWorks) {
        this.contractedWorks = cotractedWorks;
    }

    public Client(String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role) {
        super(username, firstName, lastName, email, password, age, phone, role);
    }

    public Client(UUID id, String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role) {
        super(id, username, firstName, lastName, email, password, age, phone, role);
    }

    public Client() {
    }
}
