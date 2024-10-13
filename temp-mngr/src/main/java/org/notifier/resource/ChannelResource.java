package org.notifier.resource;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.notifier.model.Channel;
import org.notifier.service.ChannelService;

@ApplicationScoped
@GraphQLApi
public class ChannelResource {

    @Inject
    ChannelService channelService;

    @Query("allChannels")
    public Multi<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    @Mutation("createChannel")
    public Uni<Channel> createChannel(String name) {
        return channelService.createChannel(name);
    }
}
