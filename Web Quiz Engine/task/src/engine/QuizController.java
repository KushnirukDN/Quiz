package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.*;


@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public @ResponseBody
    Quiz setQuiz(@Valid @RequestBody Quiz quiz) {
        try {
            quizRepository.save(quiz);
            return quiz;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.OK, "");
        }
    }


    @GetMapping(value = "/api/quizzes/{id}")
    public Optional<Quiz> getQuiz(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
        } else {
            return quizRepository.findById(id);
        }
    }

    @GetMapping("/api/quizzes")
    public Iterable<Quiz> getQuizzes() {
        try {
            return quizRepository.findAll();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.OK, " ");
        }
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public @ResponseBody
    QuizResponse result(@PathVariable("id") Long id,
                        @RequestBody ClientsAnswer answer) {
        getQuiz(id);
        QuizResponse response = new QuizResponse();

        if (Arrays.equals(answer.getClientAnswer(), (getQuiz(id).get().answer))) {
            response.setSuccess(true);
            response.setFeedback("Congratulations, you're right!");
        } else {
            response.setSuccess(false);
            response.setFeedback("Wrong answer! Please, try again.");
        }
        return response;
    }

    @DeleteMapping(value = "/api/quizzes/{id}")
    public String deleteQuiz(@PathVariable("id") Long id) {
        try   {
            quizRepository.deleteById(id);
            return String.valueOf(new ResponseStatusException(HttpStatus.NO_CONTENT, "No content"));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
        }
       // return new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}

