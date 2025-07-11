package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record UpdateResourceResource(Long topicId,
                                     String youtubeId,
                                     String title,
                                     String description
) {
    public UpdateResourceResource {
        if (topicId == null || topicId <= 0) {
            throw new IllegalArgumentException("El topicId es obligatorio y debe ser positivo");
        }
        if (youtubeId == null || youtubeId.isBlank()) {
            throw new IllegalArgumentException("El youtubeId es obligatorio");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
    }
}