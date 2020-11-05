package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

   Iterable<Quiz> findById(int id);

   Quiz findById(Integer id);

   Page<Quiz> findAll(Pageable pageable);

}