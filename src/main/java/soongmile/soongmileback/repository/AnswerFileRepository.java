package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Answer;
import soongmile.soongmileback.domain.AnswerFile;

import java.util.List;

public interface AnswerFileRepository extends JpaRepository<AnswerFile, Long> {

    List<AnswerFile> findByAnswer(Answer answer);
}
