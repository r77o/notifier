package org.notifier.service.impl;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;
import org.notifier.model.Template;
import org.notifier.model.Variable;
import org.notifier.service.VariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the VariableService interface.
 */
@ApplicationScoped
public class VariableServiceImpl implements VariableService {

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(VariableServiceImpl.class);

    @Override
    public Multi<Variable> getAllVariables() {
        LOGGER.info("Fetching all variables");
        return (Multi<Variable>) Variable.listAll();
    }

    @Override
    @WithTransaction
    public Uni<Variable> createVariable(String name, String defaultValue, Long templateId) {
        LOGGER.info("Creating variable with name: {} and default value: {} for template ID: {}", name, defaultValue, templateId);

        Variable variable = new Variable();
        variable.name = name;
        variable.defaultValue = defaultValue;

        return Template.findById(templateId)
                .onItem().transform(t -> {
                    if (t instanceof Template template) {
                        variable.template = template;
                        LOGGER.info("Template found for ID: {}", templateId);
                    } else {
                        LOGGER.warn("No template found for ID: {}", templateId);
                    }
                    return variable;
                })
                .call(variable::persistAndFlush)
                .onItem().invoke(() -> LOGGER.info("Variable created successfully: {}", variable))
                .onFailure().invoke(throwable -> LOGGER.error("Failed to create variable: {} with default value: {} for template ID: {}", name, defaultValue, templateId, throwable));
    }
}
