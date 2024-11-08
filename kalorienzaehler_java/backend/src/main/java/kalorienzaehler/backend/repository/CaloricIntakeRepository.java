package main.java.kalorienzaehler.backend.repository;

import main.java.kalorienzaehler.backend.entity.CaloricIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaloricIntakeRepository extends JpaRepository<CaloricIntake, Long> {
}
