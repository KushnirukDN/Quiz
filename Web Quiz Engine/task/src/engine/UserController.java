package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/api/register", consumes = "application/json")
    public @ResponseBody
    String regUser(@Valid @RequestBody User user)  {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            User newUser = new User();

            newUser.setPassword(encodedPassword);
            newUser.setEmail(user.getEmail());

            userRepository.save(newUser);
            return user.getEmail();
        }
    }
}