package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.*;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CourseResource;

public class UpdateCourseCommandFromResourceAssembler {
    public static UpdateCourseCommand toCommandFromResource(Long courseId, CourseResource courseResource) {
        return new UpdateCourseCommand(
                courseId,
                courseResource.name(),
                courseResource.description(),
                courseResource.category() != null ? CourseCategory.valueOf(courseResource.category()) : null,
                courseResource.price() != null ? new java.math.BigDecimal(courseResource.price().toString()) : null,
                courseResource.creatorId() != null ? new CreatorId(courseResource.creatorId()) : null,
                courseResource.image() != null ? new ImageUrl(courseResource.image()) : null,
                courseResource.language() != null ? Language.valueOf(courseResource.language()) : null,
                courseResource.difficulty() != null ? Difficulty.valueOf(courseResource.difficulty()) : null
        );
    }
    
}
