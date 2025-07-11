package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateTopicResource;

public class CreateTopicCommandFromResourceAssembler {
    public static CreateTopicCommand toCommandFromResource(CreateTopicResource resource) {
        return new CreateTopicCommand(
                resource.title(),
                resource.description(),
                new CourseId(resource.courseId())
        );
    }
}