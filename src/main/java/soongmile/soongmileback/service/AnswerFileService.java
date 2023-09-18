package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.*;
import soongmile.soongmileback.repository.AnswerFileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerFileService {

    private final AnswerFileRepository answerFileRepository;

    public AnswerFile create(Answer answer, FileEntity fileEntity) {
        AnswerFile questionFile = AnswerFile.builder()
                .fileEntity(fileEntity)
                .answer(answer)
                .build();

        return answerFileRepository.save(questionFile);
    }

    public List<AnswerFile> findByAnswer(Answer answer) {
        return answerFileRepository.findByAnswer(answer);
    }
}
