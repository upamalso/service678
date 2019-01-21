package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sc_api", schema = "678_service_db", catalog = "")
public class ScApi {
    private long apiId;
    private String apiDesc;
    private String apiName;
    private String apiXml;
    private Collection<Actions> actionsByApiId;

    @Id
    @Column(name = "api_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getApiId() {
        return apiId;
    }

    public void setApiId(long apiId) {
        this.apiId = apiId;
    }

    @Basic
    @Column(name = "api_desc", nullable = true, length = 255)
    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
    }

    @Basic
    @Column(name = "api_name", nullable = true, length = 255)
    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @Basic
    @Column(name = "api_xml", nullable = true)
    public String getApiXml() {
        return apiXml;
    }

    public void setApiXml(String apiXml) {
        this.apiXml = apiXml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScApi scApi = (ScApi) o;
        return apiId == scApi.apiId &&
                Objects.equals(apiDesc, scApi.apiDesc) &&
                Objects.equals(apiName, scApi.apiName) &&
                Objects.equals(apiXml, scApi.apiXml);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId, apiDesc, apiName, apiXml);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "scApiByApiId")
    public Collection<Actions> getActionsByApiId() {
        return actionsByApiId;
    }

    public void setActionsByApiId(Collection<Actions> actionsByApiId) {
        this.actionsByApiId = actionsByApiId;
    }
}
