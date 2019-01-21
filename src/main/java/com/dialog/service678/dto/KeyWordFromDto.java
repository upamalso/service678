package com.dialog.service678.dto;

import com.dialog.service678.entity.Actions;

public class KeyWordFromDto {

    private String firstKey;
    private String regEx;
    private int fullMatch;
    private int ignoreCase;
    private Long actionId;

    public String getFirstKey() {
        return firstKey;
    }

    public void setFirstKey(String firstKey) {
        this.firstKey = firstKey;
    }

    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }

    public int getFullMatch() {
        return fullMatch;
    }

    public void setFullMatch(int fullMatch) {
        this.fullMatch = fullMatch;
    }

    public int getIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(int ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    @Override
    public String toString() {
        return "KeyWordFromDto{" +
                "firstKey='" + firstKey + '\'' +
                ", regEx='" + regEx + '\'' +
                ", fullMatch=" + fullMatch +
                ", ignoreCase=" + ignoreCase +
                ", actionId=" + actionId +
                '}';
    }
}
