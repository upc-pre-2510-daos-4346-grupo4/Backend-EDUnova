package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.ObjectiveResource;

public class ObjectiveResourceFromEntityAssembler {
    public static ObjectiveResource toResourceFromEntity(Objective objective) {
        return new ObjectiveResource(
                objective.getId(),
                objective.getTopicId() != null ? objective.getTopicId().topicId() : null,
                objective.getTitle(),
                objective.getHeader(),
                objective.getMainParagraph(),
                objective.getFooter(),
                objective.getConclusion()
        );
    }
}
