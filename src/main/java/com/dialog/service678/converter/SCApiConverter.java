package com.dialog.service678.converter;

import com.dialog.service678.dto.SCApiFormDto;
import com.dialog.service678.entity.SCApi;
import org.springframework.stereotype.Component;


@Component
public class SCApiConverter {

    public static SCApi dtoToEntity(SCApiFormDto scApiFormDto) {
        SCApi scApi = new SCApi();
        scApi.setApiId(scApiFormDto.getApiId());
        scApi.setApiDesc(scApiFormDto.getApiDesc());
        scApi.setApiName(scApiFormDto.getApiName());
        scApi.setApiXml(scApiFormDto.getApiXml());
        scApi.setIsSmsApi(scApiFormDto.getIsSmsApi());
        return scApi;
    }

    public SCApiFormDto entityToDto(SCApi scApi) {

        SCApiFormDto scApiFormDto = new SCApiFormDto();
        scApiFormDto.setApiId(scApi.getApiId());
        scApiFormDto.setApiDesc(scApi.getApiDesc());
        scApiFormDto.setApiName(scApi.getApiName());
        scApiFormDto.setApiXml(scApi.getApiXml());
        scApiFormDto.setIsSmsApi(scApi.getIsSmsApi());
        return scApiFormDto;
    }
}
