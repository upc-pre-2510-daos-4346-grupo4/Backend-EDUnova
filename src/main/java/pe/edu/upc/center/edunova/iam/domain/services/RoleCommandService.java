package pe.edu.upc.center.edunova.iam.domain.services;

import pe.edu.upc.center.edunova.iam.domain.model.commands.SeedRolesCommand;

/**
 * Role command service
 * <p>
 *     This interface represents the service to handle role commands.
 * </p>
 */
public interface RoleCommandService {
    /**
     * Handle seed roles command
     * @param command the {@link SeedRolesCommand} command
     *
     */
    void handle(SeedRolesCommand command);
}
