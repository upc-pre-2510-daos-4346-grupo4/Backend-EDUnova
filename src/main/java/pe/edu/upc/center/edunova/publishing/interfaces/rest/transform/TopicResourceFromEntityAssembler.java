package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.TopicResource;

public class TopicResourceFromEntityAssembler {
    public static TopicResource toResourceFromEntity(Topic topic) {
        return new TopicResource(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCourseId() != null ? topic.getCourseId().courseId() : null
        );
    }
}
