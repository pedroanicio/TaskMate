package br.com.taskmate.repository;

import br.com.taskmate.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    Contract findContractByWorkId(UUID id);
    void deleteContractByWorkId(UUID workId);
}