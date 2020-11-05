package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;


import javax.validation.Valid;
import java.security.Principal;
import java.util.*;


@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    CompletionRepository completionRepository;

    @Autowired
    UserDetailsService userDetailsService;
    private final QuizService service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public @ResponseBody
    Quiz setQuiz(@Valid @RequestBody Quiz quiz) {
        try {
            quiz.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
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
    public Page<Quiz> getQuizzes(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return service.findAllAsPage(pageable);
    }

    @GetMapping("/api/quizzes/completed")
    public Page<CompletionDto> getCompletion(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             Pageable pageable) {
        pageable = PageRequest.of(page, pageSize);
        String userName = SecurityContextHolder.getContext().getAuthentication().
                getName();
        return completionRepository.findAllByUserOrderByCompletedAtDesc(userName, pageable)
                .map(QuizService::convertCompletionEntityToDto);
    }


    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public @ResponseBody
    QuizResponse solveQuiz(@PathVariable("id") Long id,
                           @RequestBody ClientsAnswer answer) {
        quizRepository.findById(id);

        QuizResponse response = new QuizResponse();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        if (Arrays.equals(answer.getClientAnswer(), (quizRepository.findById(id).get().answer))) {
            response.setSuccess(true);
            response.setFeedback("Congratulations, you're right!");
            Completion completion = new Completion();
            completion.setQuiz(id);
            completion.setUser(user);
            completionRepository.save(completion);

        } else {
            response.setSuccess(false);
            response.setFeedback("Wrong answer! Please, try again.");
        }
        return response;
    }

    @DeleteMapping(value = "/api/quizzes/{id}")
    public String deleteQuiz(@PathVariable("id") Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
        } else {
            if (SecurityContextHolder.getContext().getAuthentication().
                    getName().equals(quizRepository.findById(id).get().
                    author)) {
                quizRepository.deleteById(id);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
    }
}