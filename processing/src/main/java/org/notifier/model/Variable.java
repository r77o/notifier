package org.notifier.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;

@RegisterForReflection
@Entity
public class Variable extends PanacheEntity {
    public String name;
    public String defaultValue;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    public Template template;
}
