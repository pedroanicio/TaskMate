package br.com.taskmate.controller;

import br.com.taskmate.dto.WorkRequest;
import br.com.taskmate.model.Work;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.service.UserService;
import br.com.taskmate.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private UserService userService;

    @PostMapping("/createWork")
    public Work createWork(@RequestBody WorkRequest request) {
        // Criar nova instância de Work
        Work work = new Work();

        // Buscar o worker
        Worker worker = userService.findWorkerById(request.getWorkerId())
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        // Setar as propriedades de Work
        work.setName(request.getName());
        work.setDescription(request.getDescription());
        work.setPrice(Double.parseDouble(request.getPrice()));
        work.setLocation(request.getLocation());
        work.setWorker(worker);

        // Salvar o trabalho
        return workService.saveWork(work);
    }
}
