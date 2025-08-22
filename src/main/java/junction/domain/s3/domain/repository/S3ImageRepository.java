package junction.domain.s3.domain.repository;

import junction.domain.s3.domain.entity.S3Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface S3ImageRepository extends JpaRepository<S3Video, Long> {

}
