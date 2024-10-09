package br.com.taskmate.controller;

import br.com.taskmate.infra.security.TokenService;
import br.com.taskmate.model.user.Client;
import br.com.taskmate.model.user.User;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TaskMate API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Operation(description = "Get all users")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    public User getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.findUserById(id);
        return user.orElse(null);
    }

    // Endpoint para criar um Client
    @PostMapping("/client")
    public Client createClient(@RequestBody Client client) {
        return (Client) userService.saveUser(client);
    }

    // Endpoint para criar um Worker
    @PostMapping("/worker")
    public Worker createWorker(@RequestBody Worker worker) {
        return (Worker) userService.saveUser(worker);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            userDetails.setId(id); // Garante que o ID seja mantido
            return ResponseEntity.ok(userService.saveUser(userDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenService.validateToken(token);

        if (username.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        if (userService.findUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
