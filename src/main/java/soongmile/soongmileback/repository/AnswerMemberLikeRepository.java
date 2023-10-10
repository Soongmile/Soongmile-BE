package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.*;

public interface AnswerMemberLikeRepository extends JpaRepository<AnswerMemberLike, Long> {

    boolean existsByAnswerAndMember(Answer answer, Member member);
    AnswerMemberLike findByAnswerIdAndMemberId(Long answerId, Long memberId);
}
