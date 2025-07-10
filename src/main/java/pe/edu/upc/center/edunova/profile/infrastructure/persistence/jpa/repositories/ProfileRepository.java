package pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProfileRepository extends JpaRepository<Profile, Long>  {
}
