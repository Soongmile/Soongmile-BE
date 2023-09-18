package soongmile.soongmileback.service;

import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Answer;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;
import soongmile.soongmileback.domain.response.AnswerCreateResponse;
import soongmile.soongmileback.repository.AnswerRepository;

@Service
@AllArgsConstructor
@Builder
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final AnswerMemberLikeService answerMemberLikeService;

    public void createAnswer(AnswerCreateRequest request) {
        // 데이터가 올바르게 전달되었는지 확인
        System.out.println("AnswerCreateRequest content: " + request.getContent());

        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Question question = questionService.findEntityById(request.getQuestionId());

        // answer id X
        Answer answer = Answer.create(request, question, member);

        // answer id o
        Answer save = answerRepository.save(answer);

        question.getAnswers().add(save);
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

    @Transactional
    public AnswerCreateResponse likeById(Long id) {
        Answer answer = answerRepository.findById(id).get();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!answerMemberLikeService.exists(answer.getId(), member.getId())){
            answer.setLikes(answer.getLikes() + 1);
            answerMemberLikeService.create(answer.getId(), member.getId());
        }

        return AnswerCreateResponse
                .builder()
                .content(answer.getContent())
                .memberId(answer.getMember().getId())
                .questionId(answer.getQuestion().getId())
                .likes(answer.getLikes())
                .build();
    }
}
