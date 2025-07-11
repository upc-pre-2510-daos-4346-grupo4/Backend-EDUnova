package pe.edu.upc.center.edunova.profile.domain.services;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;

import java.util.List;

public interface ProfileQueryService {
    List<Profile> getAllProfiles();
    Profile getProfileById(Long id);
}
