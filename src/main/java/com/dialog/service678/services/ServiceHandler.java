package com.dialog.service678.services;

import com.dialog.service678.entities.Service;
import com.dialog.service678.repositories.ServiceRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ServiceHandler {

    @Autowired
    ServiceRepository serviceRepository;

    private static final Logger LOG = Logger.getLogger(ServiceHandler.class.getName());
    private ServiceHelper serviceHelper = new ServiceHelper();

    public ResponseEntity<String> createService(Map<String, Object> payload) {
        //validate data

        //convert to service object
        Service service = new Service();
        service.setName(payload.get("name").toString());
        service.setStatus(payload.get("status").toString());
        ArrayList nodeList = (ArrayList)payload.get("data");
        String serviceXml = serviceHelper.generateServiceXml(nodeList);


        //JSONObject data = new JSONObject(payload.get("status"));

        //String xmlString = this.jsonToXml(service.getJsonData());
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        service.setXmlData(serviceXml);
        service.setCreatedDateTime(currentTimestamp);
        service.setUpdatedDateTime(currentTimestamp);
        serviceRepository.save(service);


        LOG.info("Created new service with sequence id '" + service.getId() + "' and name '" + service.getName() + "'");
        return new ResponseEntity<String>("Service created with sequence id of " + service.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Service>> getAll() {
        List<Service> list = serviceRepository.findAll();
        LOG.info("Retrieved " + list.size() + " services");
        return new ResponseEntity<List<Service>>(list, HttpStatus.OK);
    }


}
