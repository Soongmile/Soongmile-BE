package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.FileEntity;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.QuestionFile;
import soongmile.soongmileback.repository.QuestionFileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionFileService {

    private final QuestionFileRepository questionFileRepository;

    public QuestionFile create(Question question, FileEntity fileEntity) {
        QuestionFile questionFile = QuestionFile.builder()
                .fileEntity(fileEntity)
                .question(question)
                .build();

        return questionFileRepository.save(questionFile);
    }

    public List<QuestionFile> findByQuestion(Question question) {
        return questionFileRepository.findByQuestion(question);
    }
}
