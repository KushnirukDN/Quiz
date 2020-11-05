package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long comId;

    private Long quizId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String user;

   /* @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;*/

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime completedAt = LocalDateTime.now();

    /*  public static Completion createCompletion(User user, Quiz quiz) {
          var completion = new Completion();
          completion.setUser(user);
          completion.setQuiz(quiz);
          return completion;
      }*/
    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuiz(Long quizId) {
        this.quizId = quizId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

   /* public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }*/

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    @Override
    public String toString() {
        return "Completion{" +
                "id=" + comId +
                ", quiz=" + quizId +
                ", user=" + user +
                ", completedAt=" + completedAt +
                '}';
    }
}