package br.com.taskmate.model.user;

import br.com.taskmate.model.user.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client extends User{


    public Client(UUID id, String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role) {
        super(id, username, firstName, lastName, email, password, age, phone, role);
    }

    public Client() {
    }
}
