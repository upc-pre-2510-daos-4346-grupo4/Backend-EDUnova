package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record CourseResource(
        Long id,
        String name,
        String description,
        String category,
        Number price,
        Long creatorId,
        String image,
        String language,
        String difficulty
) {
}
