package com.dialog.service678.dto;

import java.io.Serializable;

public class CxResponseFormDto implements Serializable {

    private Long id;
    private int resCode;
    private String resDesc;
    private String sms;
    private String sourcePort;
    private int sendToOriginatedNo;
    private int sendToNotifyNo;
    private Long scapi_id;

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

    public Long getScapi_id() {
        return scapi_id;
    }

    public void setScapi_id(Long scapi_id) {
        this.scapi_id = scapi_id;
    }

    @Override
    public String toString() {
        return "CxResponseFormDto{" +
                "id=" + id +
                ", resCode=" + resCode +
                ", resDesc='" + resDesc + '\'' +
                ", sms='" + sms + '\'' +
                ", sourcePort='" + sourcePort + '\'' +
                ", sendToOriginatedNo=" + sendToOriginatedNo +
                ", sendToNotifyNo=" + sendToNotifyNo +
                ", scapi_id=" + scapi_id +
                '}';
    }
}
