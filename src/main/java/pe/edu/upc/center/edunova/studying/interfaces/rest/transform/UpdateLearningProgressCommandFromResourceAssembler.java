package pe.edu.upc.center.edunova.studying.interfaces.rest.transform;

import pe.edu.upc.center.edunova.studying.domain.model.commands.UpdateLearningProgressCommand;
import pe.edu.upc.center.edunova.studying.interfaces.rest.resources.UpdateLearningProgressResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateLearningProgressCommandFromResourceAssembler {
    public static UpdateLearningProgressCommand toCommandFromResource(
            Long id, UpdateLearningProgressResource resource) {

        var purchased = new ArrayList<>(
                resource.purchasedCourses() != null ? resource.purchasedCourses() : List.of());

        var completed = resource.completedTopics().stream()
                .map(r -> new UpdateLearningProgressCommand.CompletedTopicDto(
                        r.courseId(),
                        new ArrayList<>(r.topicIds() != null ? r.topicIds() : List.of())
                ))
                .collect(Collectors.toList());

        return new UpdateLearningProgressCommand(id, purchased, completed);
    }
}