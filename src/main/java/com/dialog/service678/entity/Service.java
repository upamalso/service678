package com.dialog.service678.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;

@Entity
@Table(name = "service")
public class Service {

    public Service() {
    }

    public Service(String name, String jsonData, String xmlData, String status, Timestamp createdDateTime, Timestamp updatedDateTime) {
        this.name = name;
        this.jsonData = jsonData;
        this.xmlData = xmlData;
        this.status = status;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name="id")
    private Long id;

    @XmlElement(name="name")
    private String name;

    @XmlElement(name="json-data")
    @Lob
    private String jsonData;

    @XmlElement(name="xml-data")
    @Lob
    private String xmlData;

    @XmlElement(name="status")
    private String status;

    @XmlElement(name="created-date-time")
    private Timestamp createdDateTime;

    @XmlElement(name="updated-date-time")
    private Timestamp updatedDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", xmlData='" + xmlData + '\'' +
                ", status='" + status + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                '}';
    }
}
