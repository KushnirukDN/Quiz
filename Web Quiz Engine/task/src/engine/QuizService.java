package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    //@Autowired
    //  Completion completion;

    public Page<Quiz> findAllAsPage(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    public static CompletionDto convertCompletionEntityToDto(Completion completion) {
        var completionDto = new CompletionDto();
        completionDto.setId(completion.getQuizId());
        completionDto.setCompletedAt(completion.getCompletedAt());
        return completionDto;
    }
}