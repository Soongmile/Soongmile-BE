package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.request.QuestionCreateRequest;
import soongmile.soongmileback.domain.response.QuestionCreateResponse;
import soongmile.soongmileback.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void createQuestion(QuestionCreateRequest request) {
        Question question = Question.create(request);
        questionRepository.save(question);
    }

    public QuestionCreateResponse findById(Long id) {
        Question question = questionRepository.findById(id).get();

        return QuestionCreateResponse
                .builder()
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getTag())
                .field(question.getField())
                .memberId(question.getMember().getId())
                .build();
    }
}
