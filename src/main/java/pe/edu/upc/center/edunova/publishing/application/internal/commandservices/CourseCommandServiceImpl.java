package pe.edu.upc.center.edunova.publishing.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Course;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseCommandService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.CourseRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseCommandServiceImpl implements CourseCommandService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Long handle(CreateCourseCommand cmd) {

        if (courseRepository.existsByNameAndCreatorId(cmd.name(), cmd.creatorId())) {
            throw new IllegalArgumentException(
                    "Ya existe un curso con el nombre «" + cmd.name() + "» para ese creador.");
        }

        var course = new Course(cmd);

        try {
            courseRepository.save(course);
            return course.getId();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al guardar el curso: " +
                    e.getMostSpecificCause().getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Optional<Course> handle(UpdateCourseCommand cmd) {

        var course = courseRepository.findById(cmd.courseId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "El curso con id " + cmd.courseId() + " no existe."));

        courseRepository.findByNameAndCreatorId(cmd.name(), cmd.creatorId())
                .filter(c -> !c.getId().equals(cmd.courseId()))
                .ifPresent(c -> {
                    throw new IllegalArgumentException(
                            "Ya existe otro curso con el nombre «" + cmd.name() + "» para ese creador.");
                });

        course.updateInformation(
                cmd.name(),
                cmd.description(),
                cmd.category()      != null ? cmd.category()   : course.getCategory(),
                cmd.price()         != null ? cmd.price()      : course.getPrice(),
                cmd.image()         != null ? cmd.image()      : course.getImage(),
                cmd.language()      != null ? cmd.language()   : course.getLanguage(),
                cmd.difficulty()    != null ? cmd.difficulty() : course.getDifficulty()
        );

        try {
            return Optional.of(courseRepository.save(course));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(
                    "Error al actualizar curso: " + e.getMostSpecificCause().getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void handle(DeleteCourseCommand cmd) {

        if (!courseRepository.existsById(cmd.courseId())) {
            throw new IllegalArgumentException("El curso con id "
                    + cmd.courseId() + " no existe.");
        }
        try {
            courseRepository.deleteById(cmd.courseId());
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al eliminar curso: "
                    + e.getMostSpecificCause().getMessage(), e);
        }
    }
}
