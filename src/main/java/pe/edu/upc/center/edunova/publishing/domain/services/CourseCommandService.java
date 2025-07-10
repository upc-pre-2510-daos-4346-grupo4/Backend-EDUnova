package pe.edu.upc.center.edunova.publishing.domain.services;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

public interface CourseCommandService {
    Long handle(CreateCourseCommand command);
    Optional<Course> handle(UpdateCourseCommand command);
    void handle(DeleteCourseCommand command);
}
