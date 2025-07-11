package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.ResourceResource;

public class UpdateResourceCommandFromResourceAssembler {
    public static UpdateResourceCommand toCommandFromResource(Long resourceId, ResourceResource resource) {
        return new UpdateResourceCommand(
                resourceId,
                resource.topicId() != null ? new TopicId(resource.topicId()) : null,
                resource.youtubeId(),
                resource.title(),
                resource.description()
        );
    }
}
