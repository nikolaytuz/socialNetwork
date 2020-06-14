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
public class Friends {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "friend_one")
	private User friendOne;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "friend_two")
	private User friendTwo;

	private String status;

	private String created;

	public Friends() {
		this.created = Long.toString(new Date().getTime());
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> getFriendTwo() {
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("id", friendTwo.getId());
		model.put("name", friendTwo.getName());
		model.put("famile", friendTwo.getFamile());
		model.put("about", friendTwo.getStatistics());
		return model;
	}

	public void setFriendTwo(User friendTwo) {
		this.friendTwo = friendTwo;
	}

	public Map<String, Object> getFriendOne() {
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("id", friendOne.getId());
		model.put("name", friendOne.getName());
		model.put("famile", friendOne.getFamile());
		model.put("about", friendOne.getStatistics());
		return model;
		
	}

	public void setFriendOne(User friendOne) {
		this.friendOne = friendOne;
	}

}