package kalorienzaehler.backend.repository;

import kalorienzaehler.backend.entity.DailyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyGoalRepository extends JpaRepository<DailyGoal, Long> {
}