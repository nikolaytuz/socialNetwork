package com.example.socialNetwork.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String text;

	private String itsnew;

	private String created;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_one")
	private User userOne;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_two")
	private User userTwo;

	public Message() {
		this.created = Long.toString(new Date().getTime());
		itsnew = "true";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Map<String, Object> getUserOne() {
		Map<String, Object> userOneInf = new LinkedHashMap<>();
		userOneInf.put("id", userOne.getId());
		userOneInf.put("name", userOne.getName());
		userOneInf.put("famile", userOne.getFamile());
		userOneInf.put("avatar", userOne.getAvatar());
		return userOneInf;
	}

	public void setUserOne(User userOne) {
		this.userOne = userOne;
	}

	public Map<String, Object> getUserTwo() {
		Map<String, Object> userTwoInf = new LinkedHashMap<>();
		userTwoInf.put("id", userTwo.getId());
		userTwoInf.put("name", userTwo.getName());
		userTwoInf.put("famile", userTwo.getFamile());
		userTwoInf.put("avatar", userTwo.getAvatar());
		return userTwoInf;
	}

	public void setUserTwo(User userTwo) {
		this.userTwo = userTwo;
	}

	public String getItsnew() {
		return itsnew;
	}

	public void setItsnew(String its_new) {
		this.itsnew = its_new;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}