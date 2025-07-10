package pe.edu.upc.center.edunova.profile.application.internal.queryservices;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileQueryService;
import pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id " + id));
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
}