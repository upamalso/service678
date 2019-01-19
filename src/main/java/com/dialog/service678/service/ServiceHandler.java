package com.dialog.service678.service;

import com.dialog.service678.entity.Service;
import com.dialog.service678.repository.ServiceRepository;
import org.json.JSONObject;
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

        if (payload.containsKey("id")) {
            if ((Integer)payload.get("id") > 0) {
                service.setId(((Integer) payload.get("id")).longValue());
            }
        }

        service.setName(payload.get("name").toString());
        service.setStatus(payload.get("status").toString());
        ArrayList nodeList = (ArrayList)payload.get("data");
        String serviceXml = serviceHelper.generateServiceXml(nodeList);


        //JSONObject data = new JSONObject(payload.get("status"));

        //String xmlString = this.jsonToXml(service.getJsonData());
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        service.setXmlData(serviceXml);
        service.setJsonData( new JSONObject(payload).toString());
        service.setCreatedDateTime(currentTimestamp);
        service.setUpdatedDateTime(currentTimestamp);
        serviceRepository.save(service);


        LOG.info("Created new service with sequence id '" + service.getId() + "' and name '" + service.getName() + "'");
        return new ResponseEntity<String>("Service created with sequence id of " + service.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Service>> getAll() {
        List<Service> list = serviceRepository.findAll();
        LOG.info("Retrieved " + list.size() + " service");
        return new ResponseEntity<List<Service>>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<Service>> getById(Long id) {
        //List<Service> list =  serviceRepository.findById(id).get();
        List l = new ArrayList<Service>();
        l.add(serviceRepository.findById(id).get());
       // new List<Service>(serviceRepository.findById(id));
        //LOG.info("Retrieved " + list.size() + " service");
        return new ResponseEntity<List<Service>>(l, HttpStatus.OK);
    }


}
