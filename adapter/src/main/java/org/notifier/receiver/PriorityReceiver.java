package org.notifier.receiver;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.notifier.service.VendorService;

@ApplicationScoped
public class PriorityReceiver {

    private static final Logger LOGGER = Logger.getLogger(PriorityReceiver.class);

    @Inject
    VendorService vendorService;

    @Incoming("high-priority")
    public Uni<Void> receiveHighPriorityMessage(JsonObject notification) {
        return Uni.createFrom().voidItem()
                .onItem().invoke(() -> {
                    LOGGER.infof("Received high-priority message: %s", notification);
                    vendorService.sendToAppropriateVendor(notification);
                })
                .replaceWithVoid();
    }

    @Incoming("medium-priority")
    public Uni<Void> receiveMediumPriorityMessage(JsonObject notification) {
        return Uni.createFrom().voidItem()
                .onItem().invoke(() -> {
                    LOGGER.infof("Received high-priority message: %s", notification);
                    vendorService.sendToAppropriateVendor(notification);
                })
                .replaceWithVoid();
    }

    @Incoming("low-priority")
    public Uni<Void> receiveLowPriorityMessage(JsonObject notification) {
        return Uni.createFrom().voidItem()
                .onItem().invoke(() -> {
                    LOGGER.infof("Received high-priority message: %s", notification);
                    vendorService.sendToAppropriateVendor(notification);
                })
                .replaceWithVoid();
    }
}
