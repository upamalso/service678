package com.dialog.service678.converter;

import com.dialog.service678.dto.CxResponseFormDto;
import com.dialog.service678.entity.CxResponse;
import com.dialog.service678.entity.SCApi;
import org.springframework.stereotype.Component;

@Component
public class CxResponseConverter {
    public CxResponse dtoToEntity(CxResponseFormDto cxResponseFormDto, SCApi scApi) {

        CxResponse cxResponse = new CxResponse();
        cxResponse.setResCode(cxResponseFormDto.getResCode());
        cxResponse.setResDesc(cxResponseFormDto.getResDesc());
        cxResponse.setSms(cxResponseFormDto.getSms());
        cxResponse.setSourcePort(cxResponseFormDto.getSourcePort());
        cxResponse.setSendToOriginatedNo(cxResponseFormDto.getSendToOriginatedNo());
        cxResponse.setSendToNotifyNo(cxResponseFormDto.getSendToNotifyNo());
        cxResponse.setScApi(scApi);
        return cxResponse;
    }

    public CxResponseFormDto entityToDto(CxResponse cxResponse) {

        CxResponseFormDto cxResponseFormDto = new CxResponseFormDto();
        cxResponseFormDto.setId(cxResponse.getId());
        cxResponseFormDto.setResCode(cxResponse.getResCode());
        cxResponseFormDto.setResDesc(cxResponse.getResDesc());
        cxResponseFormDto.setSms(cxResponse.getSms());
        cxResponseFormDto.setSourcePort(cxResponse.getSourcePort());
        cxResponseFormDto.setSendToNotifyNo(cxResponse.getSendToNotifyNo());
        cxResponseFormDto.setSendToOriginatedNo(cxResponse.getSendToOriginatedNo());
        cxResponseFormDto.setScapi_id(cxResponse.getScApi().getApiId());

        return  cxResponseFormDto;
    }
}
