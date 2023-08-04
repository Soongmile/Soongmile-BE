package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
