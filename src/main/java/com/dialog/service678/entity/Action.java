package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACTIONS")
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long actionId;
	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public DService getService() {
		return service;
	}

	public void setService(DService service) {
		this.service = service;
	}

	public Set<KeyWord> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(Set<KeyWord> keyWords) {
		this.keyWords = keyWords;
	}

	public SCApi getScApi() {
		return scApi;
	}

	public void setScApi(SCApi scApi) {
		this.scApi = scApi;
	}

	private String actionDesc;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SERVICE_ID")
	@JsonIgnore
	private DService service;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="action")
	private Set<KeyWord> keyWords;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="API_ID")
	@JsonIgnore
	private SCApi scApi;
}
