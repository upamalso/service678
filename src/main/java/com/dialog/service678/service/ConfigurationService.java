package com.dialog.service678.service;

import com.dialog.service678.converter.ActionConverter;
import com.dialog.service678.converter.KeyWordConverter;
import com.dialog.service678.converter.ServiceConverter;
import com.dialog.service678.dto.ActionFormDto;
import com.dialog.service678.dto.KeyWordFromDto;
import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.entity.Action;
import com.dialog.service678.entity.DService;
import com.dialog.service678.entity.KeyWord;
import com.dialog.service678.entity.SCApi;
import com.dialog.service678.repository.ActionRepository;
import com.dialog.service678.repository.KeyWordRepository;
import com.dialog.service678.repository.ScApiRepository;
import com.dialog.service678.repository.ServiceRep;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public List findAll() {
//        return serviceRep.findAll().stream().map(serviceConverter::entityToDto).collect(Collectors.toList());
        return serviceRep.findServices();
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
        DService dService = serviceConverter.dtoToEntity(serviceFormDto);
        serviceRep.save(dService);

        if (serviceFormDto.getActionFormDtos().size() > 0) {
            for (ActionFormDto actionFormDto : serviceFormDto.getActionFormDtos()) {
                //Get the api details according to the apiId
                SCApi scApi = scApiRepository.findById(actionFormDto.getApiId()).orElse(new SCApi());

                //Save the action
                Action action = actionConverter.dtoToEntity(actionFormDto, dService, scApi);
                actionRepository.save(action);

                //Set the last inserted actionId
                action.setActionId(action.getActionId());

                if (actionFormDto.getKeyWordFromDtos().size() > 0) {
                    for (KeyWordFromDto keyWordFromDto : actionFormDto.getKeyWordFromDtos()) {
                        //Save keywords
                        KeyWord keyWord = KeyWordConverter.dtoToEntity(keyWordFromDto, action);
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
