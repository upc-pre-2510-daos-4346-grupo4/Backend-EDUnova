package pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByProfileId(Long profileId);
}
