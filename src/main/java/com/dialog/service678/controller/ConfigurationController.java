package com.dialog.service678.controller;

import com.dialog.service678.dto.ConfigurationFormDto;
import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.dto.response.ApiResponse;
import com.dialog.service678.service.ConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.dialog.service678.dto.response.ApiResponse.getApiResponse;

@RestController
@RequestMapping("/api/config")
@CrossOrigin
public class ConfigurationController {

    // Define the log object for this class
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ConfigurationService configurationService;

    /**
     * @Des save service configuration
     * @Param ServiceFormDto serviceFormDto
     * @Return resultcode/resultdescription in json format
     ***/
    @PostMapping(path = "/save-configuration", produces = "application/json")
    public ResponseEntity<?> saveConfiguration(
            @RequestBody ServiceFormDto serviceFormDto
    ) {
        System.out.println(serviceFormDto.toString());
        log.info("method started. ");
        Boolean result = configurationService.save(serviceFormDto);
        if (result == true){
            ApiResponse apiResponse = getApiResponse("200", "configuration.save", messageSource, log);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }else{
            ApiResponse apiResponse = getApiResponse("400", "configuration.saveFailed", messageSource, log);
            return ResponseEntity.status(HttpStatus. BAD_REQUEST).body(apiResponse);
        }

    }

    /**
     * @Des get all service configuration
     * @Return List
     ***/
    @GetMapping(path = "/all-configuration", produces = "application/json")
    public List findAllConfiguration() {
        log.info("method started. ");
        return configurationService.findAll();
    }

}
