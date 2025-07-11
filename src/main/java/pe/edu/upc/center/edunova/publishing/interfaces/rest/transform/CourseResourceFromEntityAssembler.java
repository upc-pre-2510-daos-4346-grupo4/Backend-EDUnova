package pe.edu.upc.center.edunova.publishing.interfaces.rest.transform;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CourseResource;

public class CourseResourceFromEntityAssembler {
    public static CourseResource toResourceFromEntity(Course course) {
        return new CourseResource(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getCategory() != null ? course.getCategory().toString() : null,
                course.getPrice(),
                course.getCreatorId() != null ? course.getCreatorId().creatorId() : null,
                course.getImage() != null ? course.getImage().url() : null,
                course.getLanguage() != null ? course.getLanguage().toString() : null,
                course.getDifficulty() != null ? course.getDifficulty().toString() : null
        );
    }
}
