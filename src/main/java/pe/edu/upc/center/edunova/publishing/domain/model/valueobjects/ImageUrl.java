package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public record ImageUrl(
        @NotBlank
        @Pattern(
                regexp = "^(https?://).+$",
                message = "La URL debe ser válida y comenzar con http:// o https://"
        )
        String url
) {
    public ImageUrl() {
        this("");
    }
}