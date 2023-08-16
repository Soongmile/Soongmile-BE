package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.QuestionMemberLike;

public interface QuestionMemberLikeRepository extends JpaRepository<QuestionMemberLike, Long> {

    boolean existsByQuestionAndMember(Question question, Member member);
}
