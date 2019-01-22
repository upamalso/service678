package com.dialog.service678.converter;

import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.entity.Service;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class ServiceConverter {

    public ServiceFormDto entityToDto(Service dService) {
        ServiceFormDto serviceFormDto = new ServiceFormDto();
        serviceFormDto.setServiceId(dService.getServiceId());
        serviceFormDto.setServiceName(dService.getServiceName());
        serviceFormDto.setDescription(dService.getDescription());
        serviceFormDto.setActionFormDtos(dService.getActionsByServiceId().stream().map(ActionConverter::entityToDto).collect(Collectors.toList()));
        return serviceFormDto;
    }

    public Service dtoToEntity(ServiceFormDto serviceFormDto) {
        Service dService = new Service();
//        dService.setServiceId(serviceFormDto.getServiceId());
        dService.setServiceName(serviceFormDto.getServiceName());
        dService.setDescription(serviceFormDto.getDescription());
        dService.setActionsByServiceId(serviceFormDto.getActionFormDtos().stream().map(ActionConverter::dtoToEntity).collect(Collectors.toList()));
        return dService;
    }
}
