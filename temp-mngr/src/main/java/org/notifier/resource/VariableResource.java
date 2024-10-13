package org.notifier.resource;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.notifier.model.Variable;
import org.notifier.service.VariableService;

@ApplicationScoped
@GraphQLApi
public class VariableResource {

    @Inject
    VariableService variableService;

    @Mutation("createVariable")
    public Uni<Variable> createVariable(String name, String defaultValue, Long templateId) {
        return variableService.createVariable(name, defaultValue, templateId);
    }
}
