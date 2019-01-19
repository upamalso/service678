package com.dialog.service678.converter;

import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.entity.DService;
import org.springframework.stereotype.Component;


@Component
public class ServiceConverter {

    public ServiceFormDto entityToDto(DService dService) {
        ServiceFormDto serviceFormDto = new ServiceFormDto();
        serviceFormDto.setServiceName(dService.getServiceName());
        serviceFormDto.setDescription(dService.getDescription());
        return serviceFormDto;
    }

    public DService dtoToEntity(ServiceFormDto serviceFormDto) {
        DService dService = new DService();
        dService.setServiceId(serviceFormDto.getServiceId());
        dService.setServiceName(serviceFormDto.getServiceName());
        dService.setDescription(serviceFormDto.getDescription());
        return dService;
    }
}
