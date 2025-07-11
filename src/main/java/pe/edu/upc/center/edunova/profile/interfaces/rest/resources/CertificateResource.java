package pe.edu.upc.center.edunova.profile.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateResource {
    private Long id;            // ID del certificado
    private Long profileId;     // ID del perfil
    private String title;       // Titulo
    private String issuer;      // Institucion
    private String description; // Descripcion
}
