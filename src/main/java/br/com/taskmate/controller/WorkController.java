package br.com.taskmate.controller;

import br.com.taskmate.dto.WorkRequest;
import br.com.taskmate.infra.security.TokenService;
import br.com.taskmate.model.Work;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.service.UserService;
import br.com.taskmate.service.WorkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
public class WorkController {

    @Autowired
    private WorkService workService;

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
}
