package pe.edu.upc.center.edunova.studying.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.center.edunova.studying.domain.model.aggregates.LearningProgress;
import pe.edu.upc.center.edunova.studying.domain.model.commands.CreateLearningProgressCommand;
import pe.edu.upc.center.edunova.studying.domain.model.commands.UpdateLearningProgressCommand;
import pe.edu.upc.center.edunova.studying.domain.services.LearningProgressCommandService;
import pe.edu.upc.center.edunova.studying.infrastructure.persistence.jpa.repositories.LearningProgressRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningProgressCommandServiceImpl implements LearningProgressCommandService {

    private final LearningProgressRepository repository;

    @Override
    @Transactional
    public Long handle(CreateLearningProgressCommand command) {
        var progress = new LearningProgress();
        progress.setUserId(command.getUserId());
        progress.replacePurchasedCourses(new ArrayList<>());
        progress.replaceCompletedTopics(new ArrayList<>());

        return repository.save(progress).getId();
    }

    @Override
    @Transactional
    public Optional<LearningProgress> handle(UpdateLearningProgressCommand command) {

        var optional = repository.findById(command.getId());
        if (optional.isEmpty()) return Optional.empty();

        var progress = optional.get();

        progress.replacePurchasedCourses(
                new ArrayList<>(command.getPurchasedCourses())
        );

        var newCompleted = command.getCompletedTopics().stream()
                .map(dto -> {
                    var ct = new LearningProgress.CompletedTopic();
                    ct.setCourseId(dto.getCourseId());
                    ct.setTopicIds(dto.getTopicIds());
                    return ct;
                })
                .toList();

        progress.replaceCompletedTopics(new ArrayList<>(newCompleted));

        return Optional.of(repository.save(progress));
    }
}
