package com.dialog.service678.controller;


import com.dialog.service678.entity.Service;
import com.dialog.service678.service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "service")
@CrossOrigin
public class ServiceController {

    @Autowired
    private ServiceHandler serviceHandler;

    private static final Logger LOG = Logger.getLogger(ServiceController.class.getName());

    //Create service
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createService(@RequestBody Map<String, Object> payload) {
        LOG.info("Attempting to create a service");
        return serviceHandler.createService(payload);
    }



    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Service>> getAllServices(Integer id) {
        if (id == null) {
            LOG.info("Attempting to retrieve all service");
            return serviceHandler.getAll();
        } else {
            LOG.info("Attempting to retrieve service by id : " + id);
            return serviceHandler.getById(id.longValue());
        }

    }


    //Update service
    //get all service
    //get service by id
    /*@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Service>> getServicesById(Integer id) {
        LOG.info("Attempting to retrieve service with id");
        return serviceHandler.getAll();
    }*/

    //get service by name
}
