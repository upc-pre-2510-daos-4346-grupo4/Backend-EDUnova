package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record TopicResource(
        Long id,
        String title,
        String description,
        Long courseId
) {
}
