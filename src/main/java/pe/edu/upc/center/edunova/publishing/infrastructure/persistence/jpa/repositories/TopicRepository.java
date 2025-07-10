package pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CreatorId;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndCourseId(String title, CourseId courseId);

    Optional<Topic> findByTitleAndCourseId(String title, CourseId courseId);

}
