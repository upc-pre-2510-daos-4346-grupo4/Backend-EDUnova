package pe.edu.upc.center.edunova.profile.interfaces.rest.trasnform;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;
import pe.edu.upc.center.edunova.profile.interfaces.rest.resources.CertificateResource;
import pe.edu.upc.center.edunova.profile.interfaces.rest.resources.CreateCertificateResource;

public class CertificateResourceAssembler {

    public static Certificate toEntity(CreateCertificateResource resource) {
        Certificate certificate = new Certificate();
        certificate.setProfileId(resource.getProfileId());
        certificate.setTitle(resource.getTitle());
        certificate.setIssuer(resource.getIssuer());
        certificate.setDescription(resource.getDescription());
        return certificate;
    }

    public static CertificateResource toResource(Certificate entity) {
        CertificateResource resource = new CertificateResource();
        resource.setId(entity.getId());
        resource.setProfileId(entity.getProfileId());
        resource.setTitle(entity.getTitle());
        resource.setIssuer(entity.getIssuer());
        resource.setDescription(entity.getDescription());
        return resource;
    }
}
