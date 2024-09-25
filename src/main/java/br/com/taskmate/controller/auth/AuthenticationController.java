package br.com.taskmate.controller.auth;

import br.com.taskmate.infra.security.TokenService;
import br.com.taskmate.model.user.Client;
import br.com.taskmate.model.user.User;
import br.com.taskmate.model.user.Worker;
import br.com.taskmate.model.user.dto.AuthenticationDTO;
import br.com.taskmate.model.user.dto.LoginResponseDTO;
import br.com.taskmate.model.user.dto.RegisterDTOClient;
import br.com.taskmate.model.user.dto.RegisterDTOWorker;
import br.com.taskmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        // validar credenciais
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/workerRegister")
    public ResponseEntity register(@RequestBody RegisterDTOWorker data) {
        // registrar usuário worker
        if (this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Worker newUser = new Worker(data.username(), data.firstName(), data.lastName(), data.email(), encryptedPassword, data.age(), data.phone(), data.role(), data.profession(), data.description());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/clientRegister")
    public ResponseEntity register(@RequestBody RegisterDTOClient data) {
        // registrar usuário cliente
        if (this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Client newUser = new Client(data.username(), data.firstName(), data.lastName(), data.email(), encryptedPassword, data.age(), data.phone(), data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
