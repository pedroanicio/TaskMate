package br.com.taskmate.controller;

import br.com.taskmate.model.user.Client;
import br.com.taskmate.model.user.User;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userService.findUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
