package engine;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

   Quiz findById(int id);

  //@Query("SELECT q FROM Quiz q WHERE q.id LIKE %:id%")
  //List<Quiz> findByIdLike(@Param("value") int id);
}

