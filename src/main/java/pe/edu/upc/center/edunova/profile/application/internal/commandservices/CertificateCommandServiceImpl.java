package pe.edu.upc.center.edunova.profile.application.internal.commandservices;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;
import pe.edu.upc.center.edunova.profile.domain.services.CertificateCommandService;
import pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories.CertificateRepository;
import org.springframework.stereotype.Service;

@Service
public class CertificateCommandServiceImpl implements CertificateCommandService {

    private final CertificateRepository certificateRepository;

    public CertificateCommandServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate updateCertificate(Long id, Certificate certificate) {
        certificate.setId(id);
        return certificateRepository.save(certificate);
    }

    @Override
    public void deleteCertificate(Long id) {
        certificateRepository.deleteById(id);
    }
}
