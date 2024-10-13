package org.notifier.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.notifier.model.Variable;

/**
 * Interface representing the service for managing variables.
 */
public interface VariableService {

    /**
     * Retrieves all variables from the database.
     *
     * @return a Multi containing all variables
     */
    Multi<Variable> getAllVariables();

    /**
     * Creates a new variable associated with a specified template.
     *
     * @param name        the name of the variable to be created
     * @param defaultValue the default value for the variable
     * @param templateId  the ID of the template associated with the variable
     * @return a Uni representing the created variable
     */
    Uni<Variable> createVariable(String name, String defaultValue, Long templateId);
}
