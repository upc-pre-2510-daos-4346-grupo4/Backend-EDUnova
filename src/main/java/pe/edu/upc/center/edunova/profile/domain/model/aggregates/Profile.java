package pe.edu.upc.center.edunova.profile.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String imageProfile;

    private Boolean isPremium;

    private LocalDate dateExpiration;

    public enum Gender {
        male, female
    }
}
