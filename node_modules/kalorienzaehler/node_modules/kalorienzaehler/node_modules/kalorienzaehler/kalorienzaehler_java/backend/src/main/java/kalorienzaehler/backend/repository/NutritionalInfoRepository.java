package main.java.kalorienzaehler.backend.repository;

import main.java.kalorienzaehler.backend.entity.NutritionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalInfoRepository extends JpaRepository<NutritionalInfo, Long> {
}