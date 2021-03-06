package com.dialog.service678.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;

public class ActionFormDto {

    private String actionDesc;
    private Long apiId;
    @JsonIgnore
    private Long serviceId;

    public ActionFormDto(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    //One to Many
    private Collection<KeyWordFromDto> keyWordFromDtos = new ArrayList<>();

    public ActionFormDto() {

    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Collection<KeyWordFromDto> getKeyWordFromDtos() {
        return keyWordFromDtos;
    }

    public void setKeyWordFromDtos(Collection<KeyWordFromDto> keyWordFromDtos) {
        this.keyWordFromDtos = keyWordFromDtos;
    }

    @Override
    public String toString() {
        return "ActionFormDto{" +
                "actionDesc='" + actionDesc + '\'' +
                ", apiId=" + apiId +
                ", serviceId=" + serviceId +
                ", keyWordFromDtos=" + keyWordFromDtos +
                '}';
    }
}
