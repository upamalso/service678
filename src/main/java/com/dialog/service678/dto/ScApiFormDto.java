package com.dialog.service678.dto;

import java.util.ArrayList;
import java.util.Collection;

public class ScApiFormDto {

    private Long apiId;
    private String apiName;
    private String apiDesc;
    private String apiXml;

    //One to Many
    private Collection<ActionFormDto> actionFormDtos = new ArrayList<>();

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
    }

    public String getApiXml() {
        return apiXml;
    }

    public void setApiXml(String apiXml) {
        this.apiXml = apiXml;
    }

    public Collection<ActionFormDto> getActionFormDtos() {
        return actionFormDtos;
    }

    public void setActionFormDtos(Collection<ActionFormDto> actionFormDtos) {
        this.actionFormDtos = actionFormDtos;
    }

    @Override
    public String toString() {
        return "ScApiFormDto{" +
                "apiId=" + apiId +
                ", apiName='" + apiName + '\'' +
                ", apiDesc='" + apiDesc + '\'' +
                ", apiXml='" + apiXml + '\'' +
                ", actionFormDtos=" + actionFormDtos +
                '}';
    }
}
