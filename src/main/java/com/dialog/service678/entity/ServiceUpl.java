package com.dialog.service678.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "service_upl")
public class ServiceUpl {

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
    private long serviceId;
    private String description;
    private String serviceName;

    public ServiceUpl() {
    }

    public ServiceUpl(String name, String jsonData, String xmlData, String status, Timestamp createdDateTime, Timestamp updatedDateTime) {
        this.name = name;
        this.jsonData = jsonData;
        this.xmlData = xmlData;
        this.status = status;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

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
        return "ServiceUpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", xmlData='" + xmlData + '\'' +
                ", status='" + status + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                '}';
    }

    @Id
    @Column(name = "service_id", nullable = false)
    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "service_name", nullable = true, length = 255)
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceUpl serviceUpl = (ServiceUpl) o;
        return serviceId == serviceUpl.serviceId &&
                Objects.equals(description, serviceUpl.description) &&
                Objects.equals(serviceName, serviceUpl.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, description, serviceName);
    }
}
