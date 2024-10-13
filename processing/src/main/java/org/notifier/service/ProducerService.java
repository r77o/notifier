package org.notifier.service;

import io.smallrye.mutiny.Uni;
import org.notifier.model.dto.NotificationDTO;

public interface ProducerService {

    void processNotification(Uni<NotificationDTO> notificationUni);
}
