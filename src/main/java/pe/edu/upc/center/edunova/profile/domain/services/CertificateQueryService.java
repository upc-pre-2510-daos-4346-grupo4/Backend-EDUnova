package pe.edu.upc.center.edunova.profile.domain.services;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;

import java.util.List;

public interface CertificateQueryService {
    List<Certificate> getCertificatesByProfileId(Long profileId);
}
