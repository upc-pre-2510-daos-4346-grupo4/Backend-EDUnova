package pe.edu.upc.center.edunova.profile.domain.services;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;

public interface CertificateCommandService {
    Certificate createCertificate(Certificate certificate);
    Certificate updateCertificate(Long id, Certificate certificate);
    void deleteCertificate(Long id);
}
