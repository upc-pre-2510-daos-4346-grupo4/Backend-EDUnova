package pe.edu.upc.center.edunova.profile.interfaces.rest;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Certificate;
import pe.edu.upc.center.edunova.profile.domain.services.CertificateCommandService;
import pe.edu.upc.center.edunova.profile.domain.services.CertificateQueryService;
import pe.edu.upc.center.edunova.profile.interfaces.rest.resources.CertificateResource;
import pe.edu.upc.center.edunova.profile.interfaces.rest.resources.CreateCertificateResource;
import pe.edu.upc.center.edunova.profile.interfaces.rest.trasnform.CertificateResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certificates")
@Tag(name = "Certificates", description = "Endpoints for managing certificates")
public class CertificateController {

    private final CertificateCommandService commandService;
    private final CertificateQueryService queryService;

    public CertificateController(CertificateCommandService commandService, CertificateQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<CertificateResource>> getCertificatesByProfileId(@PathVariable Long profileId) {
        var certificates = queryService.getCertificatesByProfileId(profileId)
                .stream()
                .map(CertificateResourceAssembler::toResource)
                .toList();
        return ResponseEntity.ok(certificates);
    }

    @PostMapping
    public ResponseEntity<CertificateResource> createCertificate(@RequestBody CreateCertificateResource resource) {
        Certificate certificate = CertificateResourceAssembler.toEntity(resource);
        Certificate savedCertificate = commandService.createCertificate(certificate);
        CertificateResource certificateResource = CertificateResourceAssembler.toResource(savedCertificate);
        return ResponseEntity.status(201).body(certificateResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateResource> updateCertificate(@PathVariable Long id, @RequestBody CreateCertificateResource resource) {
        Certificate certificate = CertificateResourceAssembler.toEntity(resource);
        Certificate updatedCertificate = commandService.updateCertificate(id, certificate);
        CertificateResource certificateResource = CertificateResourceAssembler.toResource(updatedCertificate);
        return ResponseEntity.ok(certificateResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        commandService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }
}
