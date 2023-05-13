package hongik.eyearoundserver.repository;

import hongik.eyearoundserver.domain.ExerciseGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseGuideRepository extends JpaRepository<ExerciseGuide, Long> {
    List<ExerciseGuide> findByGuideId(Long guideId);
}
