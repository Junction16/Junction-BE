package junction.global.jwt.domain.repository;

import junction.global.jwt.domain.entity.JsonWebToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonWebTokenRepository extends CrudRepository<JsonWebToken, String> {
}
