package com.dialog.service678.controller;


import com.dialog.service678.dto.SCApiFormDto;
import com.dialog.service678.dto.response.ApiResponse;
import com.dialog.service678.entity.SCApi;
import com.dialog.service678.entity.ServiceUpl;
import com.dialog.service678.service.SCApiService;
import com.dialog.service678.service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.dialog.service678.dto.response.ApiResponse.getApiResponse;

@RestController
@RequestMapping(value = "service")
@CrossOrigin
public class SCApiController {

    @Autowired
    private ServiceHandler serviceHandler;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SCApiService scApiService;

    // Define the log object for this class
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    private static final Logger LOG = Logger.getLogger(SCApiController.class.getName());

    //Create service
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createService(@RequestBody Map<String, Object> payload) {
        LOG.info("Attempting to create a service");
        return serviceHandler.createService(payload);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ServiceUpl>> getAllServices(Integer id) {
        if (id == null) {
            LOG.info("Attempting to retrieve all service");
            return serviceHandler.getAll();
        } else {
            LOG.info("Attempting to retrieve service by id : " + id);
            return serviceHandler.getById(id.longValue());
        }

    }

    /**
     * @Des save api configuration
     * @Param SCApiFormDto scApiFormDto
     * @Return resultcode/resultdescription in json format
     ***/
    @PostMapping(path = "/save-sc-api", produces = "application/json")
    public ResponseEntity<?> saveConfiguration(
            @RequestBody SCApiFormDto scApiFormDto
    ) {
        log.info("method started. ");
        SCApi scApi = scApiService.save(scApiFormDto);
        if (scApi.getApiId() > 0){
            ApiResponse apiResponse = getApiResponse("201", "sc-api.save", messageSource, log);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }else{
            ApiResponse apiResponse = getApiResponse("400", "sc-api.saveFailed", messageSource, log);
            return ResponseEntity.status(HttpStatus. BAD_REQUEST).body(apiResponse);
        }

    }

    /**
     * @Des save api configuration
     * @Return resultcode/resultdescription in json format
     ***/
    @GetMapping(path = "/all-sc-api", produces = "application/json")
    public ResponseEntity<?> getAllSCApi() {
        log.info("method started. ");
        List<SCApiFormDto> scApiFormDto = scApiService.findAll();
        if (scApiFormDto.size() > 0){
            ApiResponse apiResponse = getApiResponse("200", "sc-api.search", messageSource, log);
            return ResponseEntity.status(HttpStatus. OK).body(scApiFormDto);
        }else{
            ApiResponse apiResponse = getApiResponse("400", "sc-api.searchFailed", messageSource, log);
            return ResponseEntity.status(HttpStatus. BAD_REQUEST).body(apiResponse);
        }

    }


}
