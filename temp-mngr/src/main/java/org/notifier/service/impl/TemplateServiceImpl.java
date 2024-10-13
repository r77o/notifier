package org.notifier.service.impl;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;
import org.notifier.model.Channel;
import org.notifier.model.Template;
import org.notifier.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Implementation of the TemplateService interface.
 */
@ApplicationScoped
public class TemplateServiceImpl implements TemplateService {

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Override
    public Multi<Template> getAllTemplates() {
        LOGGER.info("Fetching all templates");
        return (Multi<Template>) Template.listAll();
    }

    @Override
    @WithTransaction
    public Uni<Template> getTemplate(Long id) {
        LOGGER.info("Fetching template with ID: {}", id);
        return Template.findById(id);
    }

    @Override
    @WithTransaction
    public Uni<Template> createTemplate(String name, String content, String priority, Long channelId) {
        LOGGER.info("Creating template with name: {}", name);
        Template template = new Template();
        template.name = name;
        template.content = content;
        template.createdAt = LocalDateTime.now();
        template.updatedAt = LocalDateTime.now();
        template.priority = priority;
        return Channel.findById(channelId)
                .onItem().transform(c -> {
                    if (c instanceof Channel channel){
                        template.channel = channel;
                    }
                    return template;
                }).call(template::persistAndFlush)
                .onItem().invoke(() -> LOGGER.info("Template created successfully: {}", template))
                .onFailure().invoke(throwable -> LOGGER.error("Failed to create Template: {} ", name, throwable));
    }

    @Override
    @WithTransaction
    public Uni<Template> updateTemplate(Long id, String name, String content, String priority) {
        LOGGER.info("Updating template with ID: {}", id);
        return Template.findById(id)
                .onItem().transform(t -> {
                    if (t instanceof Template template) {
                        template.name = name != null ? name : template.name;
                        template.content = content != null ? content : template.content;
                        template.updatedAt = LocalDateTime.now();
                        template.priority = priority != null ? priority : template.priority;
                        LOGGER.info("Template updated: {}", template);
                        return template;
                    }
                    LOGGER.warn("No template found for ID: {}", id);
                    return null;
                })
                .call(template -> template != null ? template.persistAndFlush() : Uni.createFrom().nullItem())
                .onFailure().invoke(throwable -> LOGGER.error("Failed to update template with ID: {}", id, throwable));
    }

    @Override
    @WithTransaction
    public Uni<Boolean> deleteTemplate(Long id) {
        LOGGER.info("Deleting template with ID: {}", id);
        return Template.deleteById(id)
                .onItem().invoke(deleted -> {
                    if (deleted) {
                        LOGGER.info("Template with ID: {} deleted successfully", id);
                    } else {
                        LOGGER.warn("No template found to delete with ID: {}", id);
                    }
                })
                .onFailure().invoke(throwable -> LOGGER.error("Failed to delete template with ID: {}", id, throwable));
    }
}
