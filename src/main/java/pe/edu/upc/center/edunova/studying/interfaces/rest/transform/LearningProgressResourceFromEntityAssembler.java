package pe.edu.upc.center.edunova.studying.interfaces.rest.transform;

import pe.edu.upc.center.edunova.studying.domain.model.aggregates.LearningProgress;
import pe.edu.upc.center.edunova.studying.interfaces.rest.resources.LearningProgressResource;

import java.util.stream.Collectors;

public class LearningProgressResourceFromEntityAssembler {
    public static LearningProgressResource toResourceFromEntity(LearningProgress entity) {
        var completed = entity.getCompletedTopics().stream()
                .map(ct -> new LearningProgressResource.CompletedTopicResource(
                        ct.getCourseId(), ct.getTopicIds()))
                .collect(Collectors.toList());

        return new LearningProgressResource(
                entity.getId(),
                entity.getUserId(),
                entity.getPurchasedCourses(),
                completed
        );
    }
}