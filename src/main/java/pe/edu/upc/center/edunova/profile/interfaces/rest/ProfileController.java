package pe.edu.upc.center.edunova.profile.interfaces.rest;

import pe.edu.upc.center.edunova.profile.domain.model.aggregates.Profile;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileCommandService;
import pe.edu.upc.center.edunova.profile.domain.services.ProfileQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Operations related to user profiles")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileQueryService profileQueryService;

    @Autowired
    private ProfileCommandService profileCommandService;

    @Operation(summary = "Get all profiles", description = "Retrieve a list of all profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileQueryService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @Operation(summary = "Get profile by ID", description = "Retrieve a specific profile by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = profileQueryService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @Operation(summary = "Update profile", description = "Update an existing profile by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Profile updatedProfile = profileCommandService.updateProfile(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }
}
