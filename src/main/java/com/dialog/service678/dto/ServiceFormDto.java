package com.dialog.service678.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceFormDto implements Serializable {


    private Long serviceId;
    private String serviceName;
    private String description;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    //One to Many
    private Collection<ActionFormDto> actionFormDtos = new ArrayList<>();

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<ActionFormDto> getActionFormDtos() {
        return actionFormDtos;
    }

    public void setActionFormDtos(Collection<ActionFormDto> actionFormDtos) {
        this.actionFormDtos = actionFormDtos;
    }

    @Override
    public String toString() {
        return "ServiceFormDto{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                ", actionFormDtos=" + actionFormDtos +
                '}';
    }
}
