package br.com.taskmate.service;

import br.com.taskmate.model.Contract;
import br.com.taskmate.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public void deleteContract(UUID id){
        contractRepository.deleteById(id);
    }

    @Transactional
    public void deleteContractByWorkId(UUID id){
        contractRepository.deleteContractByWorkId(id);
    }

    public Contract findContractByWorkId(UUID id){
        return contractRepository.findContractByWorkId(id);
    }
}
