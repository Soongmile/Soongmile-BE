package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.QuestionMemberLike;
import soongmile.soongmileback.repository.QuestionMemberLikeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionMemberLikeService {

    private final QuestionMemberLikeRepository questionMemberLikeRepository;

    public boolean exists(Long questionId, Long memberId) {
        Question question = Question.builder()
                .id(questionId)
                .build();

        Member member = Member.builder()
                .id(memberId)
                .build();

        return questionMemberLikeRepository.existsByQuestionAndMember(question, member);
    }

    @Transactional
    public void create(Long questionId, Long memberId) {
        Question question = Question.builder()
                .id(questionId)
                .build();

        Member member = Member.builder()
                .id(memberId)
                .build();

        QuestionMemberLike questionMemberLike = QuestionMemberLike.builder()
                .question(question)
                .member(member)
                .build();

        questionMemberLikeRepository.save(questionMemberLike);
    }
}
