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
    public ResponseEntity<Work> deleteWork(@PathVariable UUID workId, HttpServletRequest request){

        if(verifyAuthentication(request).isEmpty()){
            return ResponseEntity.status(401).build();
        }

        Work work = workService.findWorkById(workId);
        if(work == null){
            return ResponseEntity.status(404).build();
        }

        Worker worker = userService.findWorkerByUsername(verifyAuthentication(request));
        if (worker == null || !work.getWorker().getId().equals(worker.getId())) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

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

    @PostMapping("/contractWork/{workName}")
    public ResponseEntity<Contract> contractWork(@PathVariable String workName, @RequestBody ContractRequest requisition , HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenService.validateToken(token);

        if (username.isEmpty()) {
            System.out.println("Authentication failed for user: " + username);
            return ResponseEntity.status(401).build();
        }

        String clientId = tokenService.validateToken(token);
        Client client = userService.findClientById(UUID.fromString(clientId));
        if (client == null) {
            System.out.println("Client not found with ID: " + clientId);
            return ResponseEntity.status(404).build();
        }

        Optional<Work> workOptional = Optional.ofNullable(workService.findWorkByName(workName));
        if (workOptional.isEmpty()) {
            System.out.println("Work not found with name: " + workName);
            return ResponseEntity.status(404).build();
        }

        Work work = workOptional.get(); // Ensure work is not null
        Contract contract = new Contract();
        contract.setClient(client);
        contract.setWork(work);
        contract.setRequisition(requisition.getRequisition());

        Contract savedContract = contractService.saveContract(contract);
        return ResponseEntity.ok(savedContract);
    }

    public String verifyAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        return tokenService.validateToken(token);
    }

}
