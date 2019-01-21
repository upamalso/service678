package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "key_word", schema = "678_service_db", catalog = "")
public class KeyWord {
    private long id;
    private String firstKey;
    private int fullMatch;
    private int ignoreCase;
    private String regEx;
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
    @Column(name = "first_key", nullable = true, length = 255)
    public String getFirstKey() {
        return firstKey;
    }

    public void setFirstKey(String firstKey) {
        this.firstKey = firstKey;
    }

    @Basic
    @Column(name = "full_match", nullable = true)
    public int getFullMatch() {
        return fullMatch;
    }

    public void setFullMatch(int fullMatch) {
        this.fullMatch = fullMatch;
    }

    @Basic
    @Column(name = "ignore_case", nullable = true)
    public int getIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(int ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Basic
    @Column(name = "reg_ex", nullable = true, length = 255)
    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
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
        KeyWord keyWord = (KeyWord) o;
        return id == keyWord.id &&
                fullMatch == keyWord.fullMatch &&
                ignoreCase == keyWord.ignoreCase &&
                Objects.equals(firstKey, keyWord.firstKey) &&
                Objects.equals(regEx, keyWord.regEx) &&
                Objects.equals(actionId, keyWord.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstKey, fullMatch, ignoreCase, regEx, actionId);
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "action_id", nullable = false, insertable = false, updatable = false)
    public Actions getActionsByActionId() {
        return actionsByActionId;
    }

    public void setActionsByActionId(Actions actionsByActionId) {
        this.actionsByActionId = actionsByActionId;
    }
}
