package pe.edu.upc.center.edunova.profile.application.internal.queryservices;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;
import pe.edu.upc.center.edunova.profile.domain.services.CertificateQueryService;
import pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateQueryServiceImpl implements CertificateQueryService {

    private final CertificateRepository certificateRepository;

    public CertificateQueryServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<Certificate> getCertificatesByProfileId(Long profileId) {
        return certificateRepository.findByProfileId(profileId);
    }
}
