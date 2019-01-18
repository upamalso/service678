package com.dialog.service678.entity;

import javax.persistence.*;

@Entity
@Table(name = "SC_API")
public class SCApi {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long apiId;
	private String apiName;
	private String apiDesc;
	@Lob 
	@Column(name="api_xml", length=5120)
	private String apiXml;
	public Long getApiId() {
		return apiId;
	}
	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiDesc() {
		return apiDesc;
	}
	public void setApiDesc(String apiDesc) {
		this.apiDesc = apiDesc;
	}
	public String getApiXml() {
		return apiXml;
	}
	public void setApiXml(String apiXml) {
		this.apiXml = apiXml;
	}

}
