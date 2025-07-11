package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.*;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CourseResource;

public class UpdateCourseCommandFromResourceAssembler {

    public static UpdateCourseCommand toCommandFromResource(Long courseId,
                                                            CourseResource r) {

        CourseCategory category = r.category() != null
                ? CourseCategory.fromString(r.category())
                : null;

        Language language = r.language() != null
                ? Language.fromString(r.language())
                : null;

        Difficulty difficulty = r.difficulty() != null
                ? Difficulty.fromDisplayName(r.difficulty())
                : null;

        return new UpdateCourseCommand(
                courseId,
                r.name(),
                r.description(),
                category,
                r.price() != null ? new java.math.BigDecimal(r.price().toString()) : null,
                r.creatorId() != null ? new CreatorId(r.creatorId()) : null,
                r.image() != null ? new ImageUrl(r.image()) : null,
                language,
                difficulty
        );
    }
}
