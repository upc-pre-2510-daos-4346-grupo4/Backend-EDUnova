package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ImageUrl(String url) {
    public ImageUrl() {
        this("");
    }

    public ImageUrl {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL de la imagen no puede estar vacía");
        }
        if (!url.matches("^(https?://).+")) {
            throw new IllegalArgumentException("La URL de la imagen debe ser un enlace válido que comience con http:// o https://");
        }
    }
}