package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.TopicResource;

public class UpdateTopicCommandFromResourceAssembler {
    public static UpdateTopicCommand toCommandFromResource(Long topicId, TopicResource resource) {
        return new UpdateTopicCommand(
                topicId,
                resource.title(),
                resource.description(),
                resource.courseId() != null ? new CourseId(resource.courseId()) : null
        );
    }
}