package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Title is mandatory")
    public String title;

    @NotBlank(message = "Text is mandatory")
    public String text;

    @Size(min = 2, message = "You have to enter at least 2 options")
    @NotNull(message = "You have to enter at least 2 options")
    @ElementCollection
    @OrderColumn(name="options_array")
    public String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @OrderColumn(name = "answer_array")
    public int[] answer = new int[0];

    public Quiz() {
    }

    public Quiz(Long id, String title, String text, String[] options, int[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public int[] getAnswer() {
        return answer;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(int[] answer) {
        if (Arrays.equals(null, answer)) {
            int[] emptyAnswer = new int[0];
            this.answer = emptyAnswer;
        } else {
            this.answer = answer;
        }
    }
}

