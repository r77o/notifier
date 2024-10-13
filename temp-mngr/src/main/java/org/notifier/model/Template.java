package org.notifier.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@RegisterForReflection
@Entity
public class Template extends PanacheEntity {
    public String name;
    public String content;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public String priority;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Variable> variables;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    public Channel channel;
}