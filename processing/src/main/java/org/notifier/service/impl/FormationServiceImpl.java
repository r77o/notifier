package org.notifier.service.impl;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.notifier.model.Template;
import org.notifier.model.Variable;
import org.notifier.model.dto.NotificationDTO;
import org.notifier.model.dto.RequestDTO;
import org.notifier.service.FormationService;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FormationServiceImpl implements FormationService {

    @Override
    @WithTransaction
    public Uni<NotificationDTO> formMessage(RequestDTO requestDTO) {
        return Template.findById(requestDTO.templateId())
                .onItem().ifNotNull().transformToUni(t -> {
                    if (t instanceof Template template) {
                        String message = template.content;

                        // Fetch variables and replace them in the template content
//                        for (Variable variable : template.variables) {
//                            String variableName = variable.name;
//                            String defaultValue = variable.defaultValue;
//
//                            // Replace the variable placeholders in the template content
//                            message = message.replace(
//                                    "{{" + variableName + "}}",
//                                    Optional.ofNullable(requestDTO.variablesMap().get(variableName)).orElse(defaultValue)
//                            );
//                        }
                        NotificationDTO notificationDTO = new NotificationDTO(
                                UUID.randomUUID().toString(),
                                template.channel.name,
                                template.priority,
                                message
                        );

                        // Return the fully formed message
                        return Uni.createFrom().item(notificationDTO);
                    }
                    return Uni.createFrom().nullItem();
                });
    }
}
