package org.notifier.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.notifier.model.Template;

/**
 * Interface representing the service for managing templates.
 */
public interface TemplateService {

    /**
     * Retrieves all templates from the database.
     *
     * @return a Multi containing all templates
     */
    Multi<Template> getAllTemplates();

    /**
     * Retrieves a template by its ID.
     *
     * @param id the ID of the template to retrieve
     * @return a Uni containing the requested template
     */
    Uni<Template> getTemplate(Long id);

    /**
     * Creates a new template with the specified name and content.
     *
     * @param name    the name of the template to be created
     * @param content the content of the template to be created
     * @return a Uni representing the created template
     */
    Uni<Template> createTemplate(String name, String content, String priority, Long channelId);

    /**
     * Updates an existing template identified by its ID.
     *
     * @param id      the ID of the template to update
     * @param name    the new name for the template (optional)
     * @param content the new content for the template (optional)
     * @return a Uni representing the updated template
     */
    Uni<Template> updateTemplate(Long id, String name, String content, String priority);

    /**
     * Deletes a template identified by its ID.
     *
     * @param id the ID of the template to delete
     * @return a Uni indicating whether the deletion was successful
     */
    Uni<Boolean> deleteTemplate(Long id);
}
