package org.notifier.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.notifier.model.Channel;

/**
 * Interface representing the service for managing channels.
 */
public interface ChannelService {

    /**
     * Retrieves all channels from the database.
     *
     * @return a Multi containing all channels
     */
    Multi<Channel> getAllChannels();

    /**
     * Creates a new channel with the specified name.
     *
     * @param name the name of the channel to be created
     * @return a Uni representing the created channel
     */
    Uni<Channel> createChannel(String name);
}
