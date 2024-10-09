package br.com.taskmate.repository;

import br.com.taskmate.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkRepository extends JpaRepository<Work, UUID> {
    Work findWorkByWorkerId(UUID id);
}
