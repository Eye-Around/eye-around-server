package hongik.eyearoundserver.repository;

import hongik.eyearoundserver.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
