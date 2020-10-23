package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/api/register", consumes = "application/json")
    public @ResponseBody
    String regUser(@Valid @RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User();

        user.setPassword(encodedPassword);
        user.setEmail(user.getEmail());
        return "good";
    }
}
