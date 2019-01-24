package com.dialog.service678.converter;

import com.dialog.service678.dto.ActionFormDto;
import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.entity.DService;
import com.dialog.service678.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ServiceConverter {

    @Autowired
    private ActionRepository actionRepository;

    public ServiceFormDto entityToDto(DService dService) {
        ServiceFormDto serviceFormDto = new ServiceFormDto();
        serviceFormDto.setServiceId(dService.getServiceId());
        serviceFormDto.setServiceName(dService.getServiceName());
        serviceFormDto.setDescription(dService.getDescription());
        return serviceFormDto;
    }

    public DService dtoToEntity(ServiceFormDto serviceFormDto) {
        DService dService = new DService();
//        dService.setServiceId(serviceFormDto.getServiceId());
        dService.setServiceName(serviceFormDto.getServiceName());
        dService.setDescription(serviceFormDto.getDescription());
//        dService.setActionsByServiceId(serviceFormDto.getActionFormDtos().stream().map(ActionConverter::dtoToEntity).collect(Collectors.toList()));
        return dService;
    }



}
