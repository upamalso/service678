package com.dialog.service678.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "service_upl", schema = "678_service_db", catalog = "")
public class ServiceUpl {
    private long serviceId;
    private Timestamp createdDateTime;
    private String description;
    private Long id;
    private String jsonData;
    private String name;
    private String serviceName;
    private String status;
    private Timestamp updatedDateTime;
    private String xmlData;

    @Id
    @Column(name = "service_id", nullable = false)
    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "created_date_time", nullable = true)
    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
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
    @Column(name = "id", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "json_data", nullable = true, length = 255)
    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "service_name", nullable = true, length = 255)
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "updated_date_time", nullable = true)
    public Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    @Basic
    @Column(name = "xml_data", nullable = true, length = 255)
    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceUpl that = (ServiceUpl) o;
        return serviceId == that.serviceId &&
                Objects.equals(createdDateTime, that.createdDateTime) &&
                Objects.equals(description, that.description) &&
                Objects.equals(id, that.id) &&
                Objects.equals(jsonData, that.jsonData) &&
                Objects.equals(name, that.name) &&
                Objects.equals(serviceName, that.serviceName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(updatedDateTime, that.updatedDateTime) &&
                Objects.equals(xmlData, that.xmlData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, createdDateTime, description, id, jsonData, name, serviceName, status, updatedDateTime, xmlData);
    }
}
