package hongik.eyearoundserver.repository;

import hongik.eyearoundserver.domain.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, Long> {
}
