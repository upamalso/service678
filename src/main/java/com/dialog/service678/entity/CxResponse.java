/**
 * @author : amila hettiarachchi
 * @email : amila.hettiarachchi@axiatadigitallabs.com
 * @date : Jan 15, 2019
 */
package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CX_RESPONSE")
public class CxResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int resCode;
    private String resDesc;
    private String sms;
    private String sourcePort;
    private int sendToOriginatedNo;
    private int sendToNotifyNo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SCAPI_ID")
    @JsonIgnore
    private SCApi scApi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public SCApi getScApi() {
        return scApi;
    }

    public void setScApi(SCApi scApi) {
        this.scApi = scApi;
    }

    public int getSendToOriginatedNo() {
        return sendToOriginatedNo;
    }

    public void setSendToOriginatedNo(int sendToOriginatedNo) {
        this.sendToOriginatedNo = sendToOriginatedNo;
    }

    public int getSendToNotifyNo() {
        return sendToNotifyNo;
    }

    public void setSendToNotifyNo(int sendToNotifyNo) {
        this.sendToNotifyNo = sendToNotifyNo;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cxResponse")
    private Set<CxResponseToChannelBinder> cxResponseToChannelBinder;

    public Set<CxResponseToChannelBinder> getCxResponseToChannelBinder() {
        return cxResponseToChannelBinder;
    }

    public void setCxResponseToChannelBinder(Set<CxResponseToChannelBinder> cxResponseToChannelBinder) {
        this.cxResponseToChannelBinder = cxResponseToChannelBinder;
    }


}
