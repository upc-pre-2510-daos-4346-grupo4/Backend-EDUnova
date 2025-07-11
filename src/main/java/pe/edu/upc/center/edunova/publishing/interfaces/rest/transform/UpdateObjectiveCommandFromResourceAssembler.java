package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;


import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.ObjectiveResource;

public class UpdateObjectiveCommandFromResourceAssembler {
    public static UpdateObjectiveCommand toCommandFromResource(Long objectiveId, ObjectiveResource resource) {
        return new UpdateObjectiveCommand(
                objectiveId,
                resource.topicId() != null ? new TopicId(resource.topicId()) : null,
                resource.title(),
                resource.header(),
                resource.mainParagraph(),
                resource.footer(),
                resource.conclusion()
        );
    }
}
