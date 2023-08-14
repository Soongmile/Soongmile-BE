package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.Field;
import soongmile.soongmileback.domain.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByField(Field field);
}
