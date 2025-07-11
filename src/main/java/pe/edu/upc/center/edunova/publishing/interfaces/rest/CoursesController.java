package pe.edu.upc.center.edunova.publishing.interfaces.rest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllCoursesQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseQueryService;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CourseResource;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Course Management Endpoints")
public class CoursesController {
    private final CourseQueryService courseQueryService;
    private final CourseCommandService courseCommandService;

    public CoursesController(CourseQueryService courseQueryService, CourseCommandService courseCommandService) {
        this.courseQueryService = courseQueryService;
        this.courseCommandService = courseCommandService;
    }

    /*
    @Operation(
            summary = "Get all courses",
            description = "Retrieve a list of all courses",
            operationId = "getAllCourses",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of courses retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CourseResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        var query = new GetAllCoursesQuery();
        var courses = courseQueryService.handle(query);
        var resources = courses.stream()
                .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
    */


}
