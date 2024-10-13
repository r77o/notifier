package org.notifier.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonObject;

@ApplicationScoped
public class VendorService {

    public void sendToAppropriateVendor(JsonObject notification){
        /** TODO
         * Functionality to route the messages to appropriate channels and vendors
         */
    }
}
