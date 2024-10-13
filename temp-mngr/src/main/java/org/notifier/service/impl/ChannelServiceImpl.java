package org.notifier.service.impl;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;
import org.notifier.model.Channel;
import org.notifier.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ChannelServiceImpl implements ChannelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Override
    public Multi<Channel> getAllChannels() {
        LOGGER.info("Fetching all channels");
        return (Multi<Channel>) Channel.listAll();
    }

    @Override
    @WithTransaction
    public Uni<Channel> createChannel(String name) {
        LOGGER.info("Creating channel with name: {}", name);
        Channel channel = new Channel();
        channel.name = name;
        return channel.persistAndFlush();
    }
}
