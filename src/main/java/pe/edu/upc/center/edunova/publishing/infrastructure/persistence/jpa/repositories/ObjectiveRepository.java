package pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CreatorId;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

import java.util.Optional;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

    boolean existsByTitleAndTopicId(String title, TopicId topicId);

    Optional<Objective> findByTitleAndTopicId(String title, TopicId topicId);

}
