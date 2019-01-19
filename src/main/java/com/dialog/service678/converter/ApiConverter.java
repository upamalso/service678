package com.dialog.service678.converter;

import com.dialog.service678.dto.ScApiFormDto;
import com.dialog.service678.entity.SCApi;
import org.springframework.stereotype.Component;


@Component
public class ApiConverter {

    public static SCApi dtoToEntity(ScApiFormDto scApiFormDto) {
        SCApi scApi = new SCApi();
        scApi.setApiId(scApiFormDto.getApiId());
        scApi.setApiDesc(scApiFormDto.getApiDesc());
        scApi.setApiName(scApiFormDto.getApiName());
        scApi.setApiXml(scApiFormDto.getApiXml());
        return scApi;
    }
}
