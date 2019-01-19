package com.dialog.service678.service;

import com.dialog.service678.entity.ServiceUpl;
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

        //convert to serviceUpl object
        ServiceUpl serviceUpl = new ServiceUpl();
        serviceUpl.setName(payload.get("name").toString());
        serviceUpl.setStatus(payload.get("status").toString());
        ArrayList nodeList = (ArrayList)payload.get("data");
        String serviceXml = serviceHelper.generateServiceXml(nodeList);


        //JSONObject data = new JSONObject(payload.get("status"));

        //String xmlString = this.jsonToXml(serviceUpl.getJsonData());
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        serviceUpl.setXmlData(serviceXml);
        serviceUpl.setJsonData( new JSONObject(payload).toString());
        serviceUpl.setCreatedDateTime(currentTimestamp);
        serviceUpl.setUpdatedDateTime(currentTimestamp);
        serviceRepository.save(serviceUpl);


        LOG.info("Created new serviceUpl with sequence id '" + serviceUpl.getId() + "' and name '" + serviceUpl.getName() + "'");
        return new ResponseEntity<String>("ServiceUpl created with sequence id of " + serviceUpl.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<ServiceUpl>> getAll() {
        List<ServiceUpl> list = serviceRepository.findAll();
        LOG.info("Retrieved " + list.size() + " service");
        return new ResponseEntity<List<ServiceUpl>>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<ServiceUpl>> getById(Long id) {
        //List<ServiceUpl> list =  serviceRepository.findById(id).get();
        List l = new ArrayList<ServiceUpl>();
        l.add(serviceRepository.findById(id).get());
       // new List<ServiceUpl>(serviceRepository.findById(id));
        //LOG.info("Retrieved " + list.size() + " service");
        return new ResponseEntity<List<ServiceUpl>>(l, HttpStatus.OK);
    }


}
