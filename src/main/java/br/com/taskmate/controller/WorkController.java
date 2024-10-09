package br.com.taskmate.controller;


import br.com.taskmate.dto.ContractRequest;
import br.com.taskmate.dto.WorkResponse;
import br.com.taskmate.infra.security.TokenService;
import br.com.taskmate.model.Contract;
import br.com.taskmate.model.Work;

import br.com.taskmate.model.user.Client;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.service.ContractService;
import br.com.taskmate.service.UserService;
import br.com.taskmate.service.WorkService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/createWork")
    public ResponseEntity<Work> createWork(@RequestBody Work work, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenService.validateToken(token);

        if (username.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        Worker worker = userService.findWorkerByUsername(username);
        if (worker == null) {
            return ResponseEntity.status(404).build();
        }

        work.setWorker(worker);
        Work savedWork = workService.saveWork(work);
        return ResponseEntity.ok(savedWork);
    }

    @DeleteMapping("/deleteWork/{workId}")
    public ResponseEntity<Work> deleteWork(@PathVariable UUID workId){

        Work work = workService.findWorkById(workId);
        if(work == null){
            System.out.println("passou aqui1");
            return ResponseEntity.status(404).build();
        }

        Contract contract = contractService.findContractByWorkId(workId);

        contractService.deleteContractByWorkId(workId);

        workService.deleteWork(workId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkResponse>> getAllWorks() {
        List<Work> works = workService.findAllWorks();
        List<WorkResponse> workResponses = works.stream()
                .map(WorkResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(workResponses);
    }

    @PostMapping("/contractWork/{workId}")
    public ResponseEntity<Contract> contractWork(@PathVariable UUID workId, @RequestBody ContractRequest requisition , HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenService.validateToken(token);

        if (username.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        Client client = userService.findClientByUsername(username);
        if (client == null) {
            return ResponseEntity.status(404).build();
        }

        Optional<Work> workOptional = Optional.ofNullable(workService.findWorkById(workId));
        if (workOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Work work = workOptional.get();
        Contract contract = new Contract();
        contract.setClient(client);
        contract.setWork(work);
        contract.setRequisition(requisition.getRequisition());

        Contract savedContract = contractService.saveContract(contract);
        return ResponseEntity.ok(savedContract);
    }

}
