package org.notifier.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;

import java.util.List;

@RegisterForReflection
@Entity
public class Channel extends PanacheEntity {
    public String name;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Template> templates;
}