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
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllCoursesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetCourseByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseQueryService;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CourseResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateCourseResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.CreateCourseCommandFromResourceAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.UpdateCourseCommandFromResourceAssembler;


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


    @Operation(
            summary = "Get all courses",
            description = "Retrieve a list of all courses, optionally filtered by creatorId",
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
    public ResponseEntity<List<CourseResource>> getAllCourses(
            @RequestParam(value = "creatorId", required = false) Long creatorId) {
        var query = (creatorId != null)
                ? new GetAllCoursesQuery(creatorId)
                : new GetAllCoursesQuery();
        var courses = courseQueryService.handle(query);
        var resources = courses.stream()
                .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }


    @Operation(
            summary = "Obtener un curso por ID",
            description = "Recupera los detalles de un curso por su ID",
            operationId = "getCourseById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Curso encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CourseResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Curso no encontrado",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
        var query = new GetCourseByIdQuery(courseId);
        var optionalCourse = courseQueryService.handle(query);

        if (optionalCourse.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var resource = CourseResourceFromEntityAssembler.toResourceFromEntity(optionalCourse.get());
        return ResponseEntity.ok(resource);
    }



    @Operation(
            summary = "Crear un nuevo curso",
            description = "Registrar un nuevo curso en el sistema",
            operationId = "createCourse",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Curso creado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CourseResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de solicitud inválidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource resource) {
        var createCommand = CreateCourseCommandFromResourceAssembler.toCommandFromResource(resource);
        var courseId = courseCommandService.handle(createCommand);

        if (courseId == null || courseId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var optionalCourse = courseQueryService.handle(getCourseByIdQuery);

        if (optionalCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(optionalCourse.get());
        return new ResponseEntity<>(courseResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar un curso",
            description = "Actualiza los detalles de un curso",
            operationId = "updateCourse",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Curso actualizado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CourseResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de solicitud inválidos",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResource> updateCourse(@PathVariable Long courseId, @RequestBody CourseResource resource) {
        var updateCommand = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId, resource);
        var optionalCourse = courseCommandService.handle(updateCommand);

        if (optionalCourse.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var updatedResource = CourseResourceFromEntityAssembler.toResourceFromEntity(optionalCourse.get());
        return ResponseEntity.ok(updatedResource);
    }

    @Operation(
            summary = "Eliminar un curso",
            description = "Elimina un curso del sistema",
            operationId = "deleteCourse",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Curso eliminado exitosamente"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        var deleteCommand = new DeleteCourseCommand(courseId);
        courseCommandService.handle(deleteCommand);
        return ResponseEntity.noContent().build();
    }

}
