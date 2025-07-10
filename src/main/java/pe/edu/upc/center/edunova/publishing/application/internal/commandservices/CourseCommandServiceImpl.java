package pe.edu.upc.center.edunova.publishing.application.internal.commandservices;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseCommandService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.CourseRepository;

import java.util.Optional;

public class CourseCommandServiceImpl implements CourseCommandService {
    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @Override
    public Long handle(CreateCourseCommand command) {
        if (this.courseRepository.existsByNameAndCreatorId(command.name(), command.creatorId())) {
            throw new IllegalArgumentException("Ya existe un curso con el nombre " + command.name() + " y el creatorId proporcionado");
        }
        Course course = new Course(command);
        try {
            this.courseRepository.save(course);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el curso: " + e.getMessage());
        }
        return course.getId();
    }

    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        var courseId = command.courseId();

        if(!this.courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("El curso con id " + courseId + " no existe");
        }

        if (this.courseRepository.existsByNameAndCreatorId(command.name(), command.creatorId())) {
            Optional<Course> existingCourse = courseRepository.findByNameAndCreatorId(command.name(), command.creatorId());
            if (existingCourse.isPresent() && !existingCourse.get().getId().equals(command.courseId())) {
                throw new IllegalArgumentException("Ya existe un curso con el nombre " + command.name() + " y el creatorId proporcionado");
            }
        }

        var courseToUpdate = this.courseRepository.findById(courseId).get();

        courseToUpdate.updateInformation(
                command.name(),
                command.description(),
                command.category(),
                command.price(),
                command.image(),
                command.language(),
                command.difficulty()
        );

        try {
            var updatedCourse = this.courseRepository.save(courseToUpdate);
            return Optional.of(updatedCourse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating course: " + e.getMessage());
        }

    }

    @Override
    public void handle(DeleteCourseCommand command) {
        if (!this.courseRepository.existsById(command.courseId())) {
            throw new IllegalArgumentException("Course with id " + command.courseId() + " does not exist");
        }

        try {
            this.courseRepository.deleteById(command.courseId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting curse: " + e.getMessage());
        }
    }

}
