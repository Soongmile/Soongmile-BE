package soongmile.soongmileback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soongmile.soongmileback.domain.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
