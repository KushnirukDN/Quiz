package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface CompletionRepository extends CrudRepository<Completion, Long> {

    @Query("SELECT c FROM Completion c where c.user = :user order by c.completedAt desc")
    Page<Completion> findAllByUserOrderByCompletedAtDesc(String user, Pageable pageable);
}