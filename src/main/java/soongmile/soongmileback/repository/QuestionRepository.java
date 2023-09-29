package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTitleContaining(String keyword);
}
