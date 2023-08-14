package soongmile.soongmileback.service;

import lombok.*;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.Answer;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;
import soongmile.soongmileback.domain.response.AnswerCreateResponse;
import soongmile.soongmileback.repository.AnswerRepository;

@Service
@AllArgsConstructor
@Builder
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void createAnswer(AnswerCreateRequest request) {
        // 데이터가 올바르게 전달되었는지 확인
        System.out.println("AnswerCreateRequest content: " + request.getContent());
        Answer answer = Answer.create(request);
        answerRepository.save(answer);
    }

    public AnswerCreateResponse findById(Long id) {
        Answer answer = answerRepository.findById(id).get();

        return AnswerCreateResponse
                .builder()
                .content(answer.getContent())
                .memberId(answer.getMember().getId())
                .questionId(answer.getQuestion().getId())
                .build();
    }
}
