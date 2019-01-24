package com.dialog.service678.service;

import com.dialog.service678.converter.SCApiConverter;
import com.dialog.service678.dto.SCApiFormDto;
import com.dialog.service678.entity.SCApi;
import com.dialog.service678.repository.ScApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SCApiService {

    @Autowired
    private ScApiRepository scApiRepository;

    @Autowired
    private SCApiConverter scApiConverter;

    public SCApi save(SCApiFormDto scApiFormDto) {
        SCApi scApi = scApiConverter.dtoToEntity(scApiFormDto);
        return scApiRepository.save(scApi);
    }

    public List<SCApiFormDto> findAll() {
        return scApiRepository.findAll().stream().map(scApiConverter::entityToDto).collect(Collectors.toList());
    }
}
