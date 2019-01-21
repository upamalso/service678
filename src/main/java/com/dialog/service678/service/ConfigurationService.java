package com.dialog.service678.service;

import com.dialog.service678.converter.ActionConverter;
import com.dialog.service678.converter.KeyWordConverter;
import com.dialog.service678.converter.ServiceConverter;
import com.dialog.service678.dto.ActionFormDto;
import com.dialog.service678.dto.KeyWordFromDto;
import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.entity.Actions;
import com.dialog.service678.entity.KeyWord;
import com.dialog.service678.entity.ScApi;
import com.dialog.service678.entity.Service;
import com.dialog.service678.repository.ActionRepository;
import com.dialog.service678.repository.KeyWordRepository;
import com.dialog.service678.repository.ScApiRepository;
import com.dialog.service678.repository.ServiceRep;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ConfigurationService {

    // Define the log object for this class
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private ServiceRep serviceRep;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private ScApiRepository scApiRepository;

    @Autowired
    private KeyWordRepository keyWordRepository;

    @Autowired
    private ActionConverter actionConverter;

    public List<ServiceFormDto> findAll() {
        return serviceRep.findAll().stream().map(serviceConverter::entityToDto).collect(Collectors.toList());
//        return serviceRep.findServices();
    }

    /**
     * @Des save service configuration
     * @Param ServiceFormDto serviceFormDto
     * @Return boolean
     ***/
    public boolean save(ServiceFormDto serviceFormDto) {

        log.info("method started. ");
        log.info("serviceFormDto =" + serviceFormDto.toString());

        //Save service

        Service dService = serviceConverter.dtoToEntity(serviceFormDto);
        serviceRep.save(dService);

        System.out.println("last inserted service id = "+dService.getServiceId());
        dService.getServiceId();

        if (serviceFormDto.getActionFormDtos().size() > 0) {
            serviceFormDto.setServiceId(dService.getServiceId());
            for (ActionFormDto actionFormDto : serviceFormDto.getActionFormDtos()) {

                actionFormDto.setServiceId(dService.getServiceId());
                //Get the api details according to the apiId
//                ScApi scApi = scApiRepository.findById(actionFormDto.getApiId()).orElse(new ScApi());

                //Save the action
                Actions action = actionConverter.dtoToEntity(actionFormDto);
                actionRepository.save(action);

                //Set the last inserted actionId
                action.setActionId(action.getActionId());

                if (actionFormDto.getKeyWordFromDtos().size() > 0) {
                    for (KeyWordFromDto keyWordFromDto : actionFormDto.getKeyWordFromDtos()) {
                        keyWordFromDto.setActionId(action.getActionId());
                        //Save keywords
                        KeyWord keyWord = KeyWordConverter.dtoToEntity(keyWordFromDto);
                        keyWordRepository.save(keyWord);
                    }
                }

            }
            return true;
        }else{
            return false;
        }

    }
}
