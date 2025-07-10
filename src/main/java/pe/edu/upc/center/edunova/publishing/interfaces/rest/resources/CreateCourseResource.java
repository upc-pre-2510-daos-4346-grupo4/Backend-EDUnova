package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record CreateCourseResource(
    String name,
    String description,
    String category,
    Number price,
    Long creatorId,
    String image,
    String language,
    String difficulty
) {
    public CreateCourseResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (price == null || price.doubleValue() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
        if (creatorId == null || creatorId <= 0) {
            throw new IllegalArgumentException("El ID del creador es obligatorio y debe ser positivo");
        }
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("La imagen es obligatoria");
        }
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("El idioma es obligatorio");
        }
        if (difficulty == null || difficulty.isBlank()) {
            throw new IllegalArgumentException("La dificultad es obligatoria");
        }
    }
}
