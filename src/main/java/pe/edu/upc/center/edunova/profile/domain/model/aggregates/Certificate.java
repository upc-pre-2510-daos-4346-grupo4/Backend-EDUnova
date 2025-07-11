package pe.edu.upc.center.edunova.profile.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long profileId; // ID del perfil dueño del certificado

    private String title;       // Titulo
    private String issuer;      // Institucion
    private String description; // Descripcion
}
