package com.dialog.service678.entity;

import javax.persistence.*;

@Entity
@Table(name = "SERVICE")
public class DService {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long serviceId;
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	private String serviceName;
	private String description;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
