package br.com.taskmate.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client extends User{


    public Client(UUID id, String firstName, String lastName, String email, String password, Integer age, String phone) {
        super(id, firstName, lastName, email, password, age, phone);
    }
}
