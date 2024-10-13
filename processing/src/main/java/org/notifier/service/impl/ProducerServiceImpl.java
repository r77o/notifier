package org.notifier.service.impl;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import org.notifier.model.dto.NotificationDTO;
import org.notifier.service.ProducerService;

@ApplicationScoped
public class ProducerServiceImpl implements ProducerService {

    @Channel("high")
    Emitter<NotificationDTO> highPriorityEmitter;

    @Channel("medium")
    Emitter<NotificationDTO> mediumPriorityEmitter;

    @Channel("low")
    Emitter<NotificationDTO> lowPriorityEmitter;

    @Override
    public void processNotification(Uni<NotificationDTO> notificationUni) {
        notificationUni
                .subscribe().with(notification -> {
                    switch (notification.priority.toLowerCase()) {
                        case "high":
                            highPriorityEmitter.send(notification);
                            break;
                        case "medium":
                            mediumPriorityEmitter.send(notification);
                            break;
                        case "low":
                        default:
                            lowPriorityEmitter.send(notification);
                            break;
                    }
                });
    }
}

