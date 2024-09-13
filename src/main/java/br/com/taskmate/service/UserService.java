package br.com.taskmate.service;

import br.com.taskmate.model.user.User;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.repository.UserRepository;
import br.com.taskmate.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<Worker> findWorkerById(UUID id) {
        return workerRepository.findById(id);
    }
}

