package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateObjectiveResource;

public class CreateObjectiveCommandFromResourceAssembler {
    public static CreateObjectiveCommand toCommandFromResource(CreateObjectiveResource resource) {
        return new CreateObjectiveCommand(
                new TopicId(resource.topicId()),
                resource.title(),
                resource.header(),
                resource.mainParagraph(),
                resource.footer(),
                resource.conclusion()
        );
    }
}
