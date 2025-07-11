package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record ResourceResource(
        Long id,
        Long topicId,
        String youtubeId,
        String title,
        String description
) {

}
