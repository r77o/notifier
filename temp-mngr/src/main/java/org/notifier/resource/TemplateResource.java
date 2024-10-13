package org.notifier.resource;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.notifier.model.Template;
import org.notifier.service.TemplateService;

@ApplicationScoped
@GraphQLApi
public class TemplateResource {

    @Inject
    TemplateService templateService;

    @Query("allTemplates")
    public Multi<Template> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    @Query("templates")
    public Uni<Template> getTemplate(Long id) {
        return templateService.getTemplate(id);
    }

    @Mutation("createTemplate")
    public Uni<Template> createTemplate(String name, String content, String priority, Long channelId) {
        return templateService.createTemplate(name, content, priority, channelId);
    }

    @Mutation("updateTemplate")
    public Uni<Template> updateTemplate(Long id, String name, String content, String priority) {
        return templateService.updateTemplate(id, name, content, priority);
    }

    @Mutation("deleteTemplate")
    public Uni<Boolean> deleteTemplate(Long id) {
        return templateService.deleteTemplate(id);
    }
}
