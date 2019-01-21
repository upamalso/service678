package com.dialog.service678.converter;

import com.dialog.service678.dto.ScApiFormDto;
import com.dialog.service678.entity.ScApi;
import org.springframework.stereotype.Component;


@Component
public class ApiConverter {

    public static ScApi dtoToEntity(ScApiFormDto scApiFormDto) {
        ScApi scApi = new ScApi();
        scApi.setApiId(scApiFormDto.getApiId());
        scApi.setApiDesc(scApiFormDto.getApiDesc());
        scApi.setApiName(scApiFormDto.getApiName());
        scApi.setApiXml(scApiFormDto.getApiXml());
        return scApi;
    }
}
