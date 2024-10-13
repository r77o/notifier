package org.notifier.service;

import io.smallrye.mutiny.Uni;
import org.notifier.model.dto.NotificationDTO;
import org.notifier.model.dto.RequestDTO;

public interface FormationService {

    Uni<NotificationDTO> formMessage(RequestDTO requestDTO);
}
