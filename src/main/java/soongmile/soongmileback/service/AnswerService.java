package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.Answer;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;
import soongmile.soongmileback.domain.response.AnswerCreateResponse;
import soongmile.soongmileback.repository.AnswerRepository;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void createAnswer(AnswerCreateRequest request) {
        Answer answer = Answer.create(request);
        answerRepository.save(answer);
    }

    public AnswerCreateResponse findById(Long id) {
        Answer answer = answerRepository.findById(id).get();

        return AnswerCreateResponse
                .builder()
                .content(answer.getContent())
                .memberId(answer.getMember().getId())
                .build();
    }
}
