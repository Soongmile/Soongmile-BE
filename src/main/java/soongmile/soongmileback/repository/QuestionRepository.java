package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
