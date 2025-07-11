package pe.edu.upc.center.edunova.profile.interfaces.rest;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileCommandService;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "CRUD operations for user profiles")
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @Operation(summary = "Create profile", description = "Create a new profile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Profile created successfully")
    })
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile) {
        Profile createdProfile = profileCommandService.createProfile(newProfile);
        return ResponseEntity.status(201).body(createdProfile);
    }

    @Operation(summary = "Get all profiles", description = "Retrieve a list of all profiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profiles retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileQueryService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @Operation(summary = "Get profile by ID", description = "Retrieve a profile by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = profileQueryService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @Operation(summary = "Update profile", description = "Update an existing profile by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile updatedProfile) {
        Profile profile = profileCommandService.updateProfile(id, updatedProfile);
        return ResponseEntity.ok(profile);
    }

    @Operation(summary = "Delete profile", description = "Delete a profile by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Profile deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileCommandService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
