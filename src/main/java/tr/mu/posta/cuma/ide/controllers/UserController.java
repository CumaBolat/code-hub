package tr.mu.posta.cuma.ide.controllers;

import org.springframework.web.bind.annotation.RestController;

import tr.mu.posta.cuma.ide.models.User;
import tr.mu.posta.cuma.ide.repos.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
  private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
      userRepository.save(user);
    }

    @RequestMapping("/login")
    public String login(String param) {
      return "forward:/";
    }
    
}
