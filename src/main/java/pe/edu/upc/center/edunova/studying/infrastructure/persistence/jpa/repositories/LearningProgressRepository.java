package pe.edu.upc.center.edunova.studying.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.edunova.studying.domain.model.aggregates.LearningProgress;

import java.util.Optional;

public interface LearningProgressRepository extends JpaRepository<LearningProgress, Long> {
    Optional<LearningProgress> findByUserId(Long userId);
}