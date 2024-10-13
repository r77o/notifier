package org.notifier.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.notifier.model.dto.RequestDTO;
import org.notifier.service.FormationService;
import org.notifier.service.ProducerService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/notification/trigger")
public class NotificationResource {

    @Inject
    FormationService formationService;

    @Inject
    ProducerService producerService;

    @POST
    public Uni<Response> triggerNotification(RequestDTO requestDTO) {
        return formationService.formMessage(requestDTO)
                .onItem().transform(notificationDTO -> {
                    producerService.processNotification(Uni.createFrom().item(notificationDTO));
                    return Response.status(Response.Status.CREATED).build();
                });
    }
}
