package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record CreateTopicResource(
        String title,
        String description,
        Long courseId
) {
    public CreateTopicResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("El courseId es obligatorio y debe ser positivo");
        }
    }
}