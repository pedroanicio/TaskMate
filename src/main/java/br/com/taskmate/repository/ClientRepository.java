package br.com.taskmate.repository;

import br.com.taskmate.model.user.Client;
import br.com.taskmate.model.user.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByEmail(String email);

    Client findByUsername(String username);

}

