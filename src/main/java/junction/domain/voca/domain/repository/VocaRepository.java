package junction.domain.voca.domain.repository;

import junction.domain.user.domain.entity.User;
import junction.domain.voca.domain.entity.Voca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocaRepository extends JpaRepository<Voca, Long> {

    // 유저 엔티티 자체로 조회
    List<Voca> findByUser(User user);

    List<Voca> findByUserAndVocaType(User user, String vocaType);
}
