package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.*;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateCourseResource;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreateCourseCommandFromResourceAssembler {
    public static CreateCourseCommand toCommandFromResource(CreateCourseResource resource) {

        CourseCategory category = CourseCategory.valueOf(resource.category());

        return new CreateCourseCommand(
                resource.name(),
                resource.description(),
                CourseCategory.valueOf(resource.category()),
                new BigDecimal(resource.price().toString()).setScale(2, RoundingMode.UP),
                new CreatorId(resource.creatorId()),
                new ImageUrl(resource.image()),
                Language.valueOf(resource.language()),
                Difficulty.valueOf(resource.difficulty())
        );

    }

}
