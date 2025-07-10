package pe.edu.upc.center.edunova.publishing.domain.services;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllCoursesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetCourseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {
    List<Course> handle(GetAllCoursesQuery query);
    Optional<Course> handle(GetCourseByIdQuery query);
}
