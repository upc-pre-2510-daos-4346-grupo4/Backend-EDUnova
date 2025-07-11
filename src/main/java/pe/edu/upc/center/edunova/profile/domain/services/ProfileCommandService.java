package pe.edu.upc.center.edunova.profile.domain.services;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;

public interface ProfileCommandService {
    Profile updateProfile(Long id, Profile profile);
    Profile createProfile(Profile profile);
    void deleteProfile(Long profileId);
}
