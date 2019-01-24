package com.dialog.service678.controller;

import com.dialog.service678.dto.response.ApiResponse;
import com.dialog.service678.dto.CxResponseFormDto;
import com.dialog.service678.entity.CxResponse;
import com.dialog.service678.service.CxResponseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dialog.service678.dto.response.ApiResponse.getApiResponse;

@RestController
@RequestMapping("/api/cx-response")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CxResponseController {

    // Define the log object for this class
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CxResponseService cxResponseService;

    /**
     * @Des save cx_response configuration
     * @Param CxResponseFormDto cxResponseFormDto
     * @Return resultcode/resultdescription in json format
     ***/
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> save(
            @RequestBody CxResponseFormDto cxResponseFormDto
    ) {
        log.info("method started. ");
        CxResponse cxResponse = cxResponseService.save(cxResponseFormDto);
        if (cxResponse.getId() > 0){
            ApiResponse apiResponse = getApiResponse("201", "cx-response.save", messageSource, log);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }else{
            ApiResponse apiResponse = getApiResponse("400", "cx-response.saveFailed", messageSource, log);
            return ResponseEntity.status(HttpStatus. BAD_REQUEST).body(apiResponse);
        }

    }

    /**
     * @Des find all cx_response configuration
     * @Param CxResponseFormDto cxResponseFormDto
     * @Return resultcode/resultdescription in json format
     ***/
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAll() {
        log.info("method started. ");
        List<CxResponseFormDto> cxResponseFormDtos = cxResponseService.findAll();
        if (cxResponseFormDtos.size() > 0){
            ApiResponse apiResponse = getApiResponse("200", "cx-response.findAll", messageSource, log);
            return ResponseEntity.status(HttpStatus.OK).body(cxResponseFormDtos);
        }else{
            ApiResponse apiResponse = getApiResponse("400", "cx-response.findAllFailed", messageSource, log);
            return ResponseEntity.status(HttpStatus. BAD_REQUEST).body(apiResponse);
        }

    }
}
