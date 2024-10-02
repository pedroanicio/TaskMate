package br.com.taskmate.service;

import br.com.taskmate.model.Contract;
import br.com.taskmate.model.Work;
import br.com.taskmate.repository.ContractRepository;
import br.com.taskmate.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    public List<Work> findAllWorks() {
        return workRepository.findAll();
    }

    public Work findWorkById(UUID id) {
        return workRepository.findById(id).orElse(null);
    }

    public Work findWorkByWorkerId(UUID id) {
        return workRepository.findWorkByWorkerId(id);
    }

    public Work saveWork(Work work) {
        return workRepository.save(work);
    }

    public void deleteWork(UUID id) {
        workRepository.deleteById(id);
    }


}
