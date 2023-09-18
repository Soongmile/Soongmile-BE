package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.FileEntity;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.QuestionFile;

import java.util.List;

public interface QuestionFileRepository extends JpaRepository<QuestionFile, Long> {

    List<QuestionFile> findByQuestion(Question question);
}
