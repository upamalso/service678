/**
 * @author : amila hettiarachchi
 * @email : amila.hettiarachchi@axiatadigitallabs.com
 * @date : Jan 21, 2019
 */
package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "RESP_SMS_TO_CHANNEL_MAPPER")
public class CxResponseToChannelBinder {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChannelRef getChannelRef() {
        return channelRef;
    }

    public void setChannelRef(ChannelRef channelRef) {
        this.channelRef = channelRef;
    }

    public CxResponse getCxResponse() {
        return cxResponse;
    }

    public void setCxResponse(CxResponse cxResponse) {
        this.cxResponse = cxResponse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHANNEL_REF")
    @JsonIgnore
    private ChannelRef channelRef;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CXRESPONSE_REF")
    @JsonIgnore
    private CxResponse cxResponse;
}
