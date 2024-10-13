package org.notifier.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Map;

@RegisterForReflection
public record RequestDTO(Long templateId, Map<String, String> variablesMap) {

    @Override
    public String toString() {
        return "RequestDTO{" +
                "templateId=" + templateId +
                ", variablesMap=" + variablesMap +
                '}';
    }

}
