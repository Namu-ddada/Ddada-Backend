package ssafy.ddada.domain.gym.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.ddada.domain.gym.entity.Gym;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {

    @EntityGraph(attributePaths = {"gymAdmin", "courts"})
    @Query("""
        SELECT g
        FROM Gym g
        WHERE g.gymAdmin.id = :gymAdminId
    """)
    Optional<Gym> getGymsById(@Param("gymAdminId") Long gymAdminId);

}
