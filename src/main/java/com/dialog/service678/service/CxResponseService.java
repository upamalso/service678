package com.dialog.service678.service;

import com.dialog.service678.converter.CxResponseConverter;
import com.dialog.service678.dto.CxResponseFormDto;
import com.dialog.service678.entity.CxResponse;
import com.dialog.service678.entity.SCApi;
import com.dialog.service678.repository.CxResponseRepository;
import com.dialog.service678.repository.ScApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CxResponseService {

    @Autowired
    private CxResponseConverter cxResponseConverter;

    @Autowired
    private ScApiRepository scApiRepository;

    @Autowired
    private CxResponseRepository cxResponseRepository;

    public CxResponse save(CxResponseFormDto cxResponseFormDto) {

        SCApi scApi = scApiRepository.findById(cxResponseFormDto.getScapi_id()).get();

        CxResponse cxResponse = cxResponseConverter.dtoToEntity(cxResponseFormDto, scApi);
        return cxResponseRepository.save(cxResponse);
    }

    public List<CxResponseFormDto> findAll() {
        return cxResponseRepository.findAll().stream().map(cxResponseConverter::entityToDto).collect(Collectors.toList());
    }
}
