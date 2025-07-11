package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.ResourceResource;

public class ResourceResourceFromEntityAssembler {
    public static ResourceResource toResourceFromEntity(Resource resource) {
        return new ResourceResource(
                resource.getId(),
                resource.getTopicId() != null ? resource.getTopicId().topicId() : null,
                resource.getYoutubeId(),
                resource.getTitle(),
                resource.getDescription()
        );
    }
}
