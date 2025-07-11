package pe.edu.upc.center.edunova.studying.domain.model.commands;

import lombok.Getter;

@Getter
public class CreateLearningProgressCommand {
    private final Long userId;
    public CreateLearningProgressCommand(Long userId) { this.userId = userId; }
}