package com.dialog.service678.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cx_response", schema = "678_service_db", catalog = "")
public class CxResponse {
    private long id;
    private int resCode;
    private String resDesc;
    private String sms;
    private String sourcePort;
    private int sendToNotifyNo;
    private int sendToOriginatedNo;
    private Long actionId;
    private Actions actionsByActionId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "res_code", nullable = true)
    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    @Basic
    @Column(name = "res_desc", nullable = true, length = 255)
    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    @Basic
    @Column(name = "sms", nullable = true, length = 255)
    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Basic
    @Column(name = "source_port", nullable = true, length = 255)
    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    @Basic
    @Column(name = "send_to_notify_no", nullable = true)
    public int getSendToNotifyNo() {
        return sendToNotifyNo;
    }

    public void setSendToNotifyNo(int sendToNotifyNo) {
        this.sendToNotifyNo = sendToNotifyNo;
    }

    @Basic
    @Column(name = "send_to_originated_no", nullable = true)
    public int getSendToOriginatedNo() {
        return sendToOriginatedNo;
    }

    public void setSendToOriginatedNo(int sendToOriginatedNo) {
        this.sendToOriginatedNo = sendToOriginatedNo;
    }

    @Basic
    @Column(name = "action_id", nullable = false)
    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CxResponse that = (CxResponse) o;
        return id == that.id &&
                resCode == that.resCode &&
                sendToNotifyNo == that.sendToNotifyNo &&
                sendToOriginatedNo == that.sendToOriginatedNo &&
                Objects.equals(resDesc, that.resDesc) &&
                Objects.equals(sms, that.sms) &&
                Objects.equals(sourcePort, that.sourcePort) &&
                Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resCode, resDesc, sms, sourcePort, sendToNotifyNo, sendToOriginatedNo, actionId);
    }

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "action_id", nullable = false, insertable = false, updatable = false)
    public Actions getActionsByActionId() {
        return actionsByActionId;
    }

    public void setActionsByActionId(Actions actionsByActionId) {
        this.actionsByActionId = actionsByActionId;
    }
}
