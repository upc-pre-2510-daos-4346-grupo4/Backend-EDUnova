package pe.edu.upc.center.edunova.studying.interfaces.rest.transform;

import pe.edu.upc.center.edunova.studying.domain.model.commands.UpdateLearningProgressCommand;
import pe.edu.upc.center.edunova.studying.interfaces.rest.resources.UpdateLearningProgressResource;

import java.util.stream.Collectors;

public class UpdateLearningProgressCommandFromResourceAssembler {
    public static UpdateLearningProgressCommand toCommandFromResource(
            Long id, UpdateLearningProgressResource resource) {

        var completed = resource.completedTopics().stream()
                .map(r -> new UpdateLearningProgressCommand.CompletedTopicDto(
                        r.courseId(), r.topicIds()))
                .collect(Collectors.toList());

        return new UpdateLearningProgressCommand(id,
                resource.purchasedCourses(), completed);
    }
}