package pe.edu.upc.center.edunova.publishing.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllCoursesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetCourseByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseQueryService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {
    private final CourseRepository courseRepository;

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> handle(GetCourseByIdQuery query) {
        return courseRepository.findById(query.courseId());
    }

    @Override
    public List<Course> handle(GetAllCoursesQuery query) {
        return courseRepository.findAll();
    }
}
