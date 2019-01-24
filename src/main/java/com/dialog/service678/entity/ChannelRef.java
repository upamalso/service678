/**
 * @author : amila hettiarachchi
 * @email : amila.hettiarachchi@axiatadigitallabs.com
 * @date : Jan 15, 2019
 */
package com.dialog.service678.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHANNEL_REF")
public class ChannelRef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long channelId;
    private String channelName;
    private String description;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
