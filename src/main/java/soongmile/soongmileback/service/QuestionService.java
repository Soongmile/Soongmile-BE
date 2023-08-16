package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Member;
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
    private final QuestionMemberLikeService questionMemberLikeService;

    @Transactional
    public void createQuestion(QuestionCreateRequest request) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Question question = Question.create(request, member);
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
                .likes(question.getLikes())
                .answerList(collect)
                .build();
    }

    @Transactional
    public QuestionCreateResponse likeById(Long id) {
        Question question = questionRepository.findById(id).get();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!questionMemberLikeService.exists(question.getId(), member.getId())){
            question.setLikes(question.getLikes() + 1);
            questionMemberLikeService.create(question.getId(), member.getId());
        }

        List<AnswerView> collect = question.getAnswers().stream().map(AnswerView::create).collect(Collectors.toList());
        return QuestionCreateResponse
                .builder()
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getTag())
                .field(question.getField())
                .memberId(question.getMember().getId())
                .hits(question.getHits())
                .likes(question.getLikes())
                .answerList(collect)
                .build();
    }

    public Question findEntityById(Long id) {
        return questionRepository.findById(id).get();
    }
}
