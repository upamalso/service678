package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Service {
    private long serviceId;
    private String description;
    private String serviceName;
    private Collection<Actions> actionsByServiceId;

    @Id
    @Column(name = "service_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "service_name", nullable = true, length = 255)
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return serviceId == service.serviceId &&
                Objects.equals(description, service.description) &&
                Objects.equals(serviceName, service.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, description, serviceName);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "serviceByServiceId")
    public Collection<Actions> getActionsByServiceId() {
        return actionsByServiceId;
    }

    public void setActionsByServiceId(Collection<Actions> actionsByServiceId) {
        this.actionsByServiceId = actionsByServiceId;
    }
}
