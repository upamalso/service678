package com.dialog.service678.controllers;


import com.dialog.service678.entities.Service;
import com.dialog.service678.services.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value="service")
@CrossOrigin
public class ServiceController {

    @Autowired
    private ServiceHandler serviceHandler;

    private static final Logger LOG = Logger.getLogger(ServiceController.class.getName());

        //Create service
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createService(@RequestBody Map<String, Object> payload) {
        LOG.info("Attempting to create a service");
        return serviceHandler.createService(payload);
    }

        @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Service>> getAllServices() {
        LOG.info("Attempting to retrieve all services");
        return serviceHandler.getAll();
    }



    //Update service
    //get all services
    //get service by id
    //get service by name
}