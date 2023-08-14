package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.request.QuestionCreateRequest;
import soongmile.soongmileback.domain.response.AnswerView;
import soongmile.soongmileback.domain.response.QuestionCreateResponse;
import soongmile.soongmileback.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void createQuestion(QuestionCreateRequest request) {
        Question question = Question.create(request);
        questionRepository.save(question);
    }

    @Transactional
    public QuestionCreateResponse findById(Long id) {
        Question question = questionRepository.findById(id).get();
        question.setHits(question.getHits() + 1);

        List<AnswerView> collect = question.getAnswers().stream().map(AnswerView::create).collect(Collectors.toList());

        return QuestionCreateResponse
                .builder()
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getTag())
                .field(question.getField())
                .memberId(question.getMember().getId())
                .hits(question.getHits())
                .answerList(collect)
                .build();
    }

    public Question findEntityById(Long id) {
        return questionRepository.findById(id).get();
    }
}
