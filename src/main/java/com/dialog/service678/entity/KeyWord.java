/**
 * @author : amila hettiarachchi
 * @email  : amila.hettiarachchi@axiatadigitallabs.com
 * @date   : Jan 15, 2019
 */
package com.dialog.service678.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "KEY_WORD")
public class KeyWord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstKey;
	private String regEx;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="ACTION_ID")
	@JsonIgnore
	private Action action;
	private int fullMatch;
	private int ignoreCase;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
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

}
