package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateResourceResource;

public class CreateResourceCommandFromResourceAssembler {
    public static CreateResourceCommand toCommandFromResource(CreateResourceResource resource) {
        return new CreateResourceCommand(
                new TopicId(resource.topicId()),
                resource.youtubeId(),
                resource.title(),
                resource.description()
        );
    }
}
