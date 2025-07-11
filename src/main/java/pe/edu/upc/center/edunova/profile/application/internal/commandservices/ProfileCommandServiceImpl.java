package pe.edu.upc.center.edunova.profile.application.internal.commandservices;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileCommandService;
import pe.edu.upc.center.edunova.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Long profileId, Profile profile) {
        Optional<Profile> existingProfileOpt = profileRepository.findById(profileId);
        if (existingProfileOpt.isEmpty()) {
            throw new RuntimeException("Profile not found with id: " + profileId);
        }
        Profile existingProfile = existingProfileOpt.get();

        existingProfile.setName(profile.getName());
        existingProfile.setLastname(profile.getLastname());
        existingProfile.setGender(profile.getGender());
        existingProfile.setImageProfile(profile.getImageProfile());
        existingProfile.setIsPremium(profile.getIsPremium());
        existingProfile.setDateExpiration(profile.getDateExpiration());

        return profileRepository.save(existingProfile);
    }

    @Override
    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }
}
