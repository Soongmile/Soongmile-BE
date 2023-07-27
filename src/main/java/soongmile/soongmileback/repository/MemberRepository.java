package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
