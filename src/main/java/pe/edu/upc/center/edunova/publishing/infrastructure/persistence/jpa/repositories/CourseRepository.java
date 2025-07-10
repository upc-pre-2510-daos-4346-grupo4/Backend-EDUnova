package pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CreatorId;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByNameAndCreatorId(String name, CreatorId creatorId);
    Optional<Course> findByNameAndCreatorId(String name, CreatorId creatorId);
}
