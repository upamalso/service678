package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Actions {
    private long actionId;
    private String actionDesc;
    private Long serviceId;
    private Long apiId;
    private Service serviceByServiceId;
    private ScApi scApiByApiId;
    private Collection<CxResponse> cxResponsesByActionId;
    private Collection<KeyWord> keyWordsByActionId;

    @Id
    @Column(name = "action_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    @Basic
    @Column(name = "action_desc", nullable = true, length = 255)
    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    @Basic
    @Column(name = "service_id", nullable = false)
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "api_id", nullable = false)
    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actions actions = (Actions) o;
        return actionId == actions.actionId &&
                Objects.equals(actionDesc, actions.actionDesc) &&
                Objects.equals(serviceId, actions.serviceId) &&
                Objects.equals(apiId, actions.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionId, actionDesc, serviceId, apiId);
    }

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false, insertable = false, updatable = false)
    public Service getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(Service serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }


    @ManyToOne
    @JoinColumn(name = "api_id", referencedColumnName = "api_id", nullable = false, insertable = false, updatable = false)
    public ScApi getScApiByApiId() {
        return scApiByApiId;
    }

    public void setScApiByApiId(ScApi scApiByApiId) {
        this.scApiByApiId = scApiByApiId;
    }


    @OneToMany(mappedBy = "actionsByActionId")
    public Collection<CxResponse> getCxResponsesByActionId() {
        return cxResponsesByActionId;
    }

    public void setCxResponsesByActionId(Collection<CxResponse> cxResponsesByActionId) {
        this.cxResponsesByActionId = cxResponsesByActionId;
    }

    @OneToMany(mappedBy = "actionsByActionId")
    public Collection<KeyWord> getKeyWordsByActionId() {
        return keyWordsByActionId;
    }

    public void setKeyWordsByActionId(Collection<KeyWord> keyWordsByActionId) {
        this.keyWordsByActionId = keyWordsByActionId;
    }
}
