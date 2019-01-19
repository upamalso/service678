package com.dialog.service678.dto;

import java.util.ArrayList;
import java.util.Collection;

public class ConfigurationFormDto {

    private String serviceName;
    private String description;

    private Collection<ServiceFormDto> serviceFormDtos = new ArrayList<>();

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

    public Collection<ServiceFormDto> getServiceFormDtos() {
        return serviceFormDtos;
    }

    public void setServiceFormDtos(Collection<ServiceFormDto> serviceFormDtos) {
        this.serviceFormDtos = serviceFormDtos;
    }

    @Override
    public String toString() {
        return "ConfigurationFormDto{" +
                "serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                ", serviceFormDtos=" + serviceFormDtos +
                '}';
    }
}
