package pe.edu.upc.center.edunova.profile.application.internal.commandservices;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileCommandService;
import pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile updateProfile(Long id, Profile profile) {
        Optional<Profile> existingProfileOpt = profileRepository.findById(id);
        if (existingProfileOpt.isPresent()) {
            Profile existingProfile = existingProfileOpt.get();

            // Actualizar campos permitidos
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setUsername(profile.getUsername());
            existingProfile.setFirstName(profile.getFirstName());
            existingProfile.setLastName(profile.getLastName());
            existingProfile.setDateOfBirth(profile.getDateOfBirth());
            existingProfile.setGender(profile.getGender());
            existingProfile.setImageUrl(profile.getImageUrl());
            existingProfile.setIsPremium(profile.getIsPremium());

            return profileRepository.save(existingProfile);
        } else {
            throw new RuntimeException("Profile not found with id " + id);
        }
    }
}
