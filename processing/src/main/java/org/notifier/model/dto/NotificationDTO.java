package org.notifier.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class NotificationDTO {

    public String id;
    public String channel;
    public String priority;
    public String message;


    @Override
    public String toString() {
        return "NotificationDTO{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                ", priority='" + priority + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
    /**
     * Default constructor required for Jackson serializer
     */
    public NotificationDTO() { }

    public NotificationDTO(String id, String channel, String priority, String message) {
        this.id = id;
        this.channel = channel;
        this.priority = priority;
        this.message = message;
    }
}