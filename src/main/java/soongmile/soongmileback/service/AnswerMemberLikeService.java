package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.*;
import soongmile.soongmileback.repository.AnswerMemberLikeRepository;
import soongmile.soongmileback.repository.QuestionMemberLikeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerMemberLikeService {

    private final AnswerMemberLikeRepository answerMemberLikeRepository;

    public boolean exists(Long answerId, Long memberId) {
        Answer answer = Answer.builder()
                .id(answerId)
                .build();

        Member member = Member.builder()
                .id(memberId)
                .build();

        return answerMemberLikeRepository.existsByAnswerAndMember(answer, member);
    }

    @Transactional
    public void create(Long answerId, Long memberId) {
        Answer answer = Answer.builder()
                .id(answerId)
                .build();

        Member member = Member.builder()
                .id(memberId)
                .build();

        AnswerMemberLike answerMemberLike = AnswerMemberLike.builder()
                .answer(answer)
                .member(member)
                .build();

        answerMemberLikeRepository.save(answerMemberLike);
    }

    @Transactional
    public void delete(Long answerId, Long memberId) {
        AnswerMemberLike answerMemberLike = answerMemberLikeRepository.findByAnswerIdAndMemberId(answerId, memberId);

        if (answerMemberLike != null) {
            answerMemberLikeRepository.delete(answerMemberLike);
        }
    }
}
