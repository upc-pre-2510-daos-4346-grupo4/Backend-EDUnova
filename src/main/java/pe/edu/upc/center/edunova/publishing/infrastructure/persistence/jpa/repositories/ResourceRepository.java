package pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    boolean existsByTopicIdAndYoutubeIdAndTitle(TopicId topicId, String youtubeId, String title);

    Optional<Resource> findByTopicIdAndYoutubeIdAndTitle(TopicId topicId, String youtubeId, String title);

}
