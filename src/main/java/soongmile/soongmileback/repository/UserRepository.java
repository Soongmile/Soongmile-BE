package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
