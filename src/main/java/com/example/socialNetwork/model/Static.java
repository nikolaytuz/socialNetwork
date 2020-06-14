package com.example.socialNetwork.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//import com.example.socialNetwork.model.User;

@Entity // This tells Hibernate to make a table out of this class
public class Static {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String sity;

	private String scholl;
	
	private String Avatar;
	
	private String date;

	private String date_close_scholl;

	private String date_begin_scholl;

	private Integer friend_count;

	private Integer image_count;

	private Integer subscribe_count;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSity() {
		return sity;
	}

	public void setSity(String sity) {
		this.sity = sity;
	}

	public String getScholl() {
		return scholl;
	}

	public void setScholl(String scholl) {
		this.scholl = scholl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getFriend_count() {
		return friend_count;
	}

	public void setFriend_count(Integer friend_count) {
		this.friend_count = friend_count;
	}

	public Integer getImage_count() {
		return image_count;
	}

	public void setImage_count(Integer image_count) {
		this.image_count = image_count;
	}

	public Integer getSubscribe_count() {
		return subscribe_count;
	}

	public void setSubscribe_count(Integer subscribe_count) {
		this.subscribe_count = subscribe_count;
	}

	public String getDate_close_scholl() {
		return date_close_scholl;
	}

	public void setDate_close_scholl(String date_close_scholl) {
		this.date_close_scholl = date_close_scholl;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public String getDate_begin_scholl() {
		return date_begin_scholl;
	}

	public void setDate_begin_scholl(String date_begin_scholl) {
		this.date_begin_scholl = date_begin_scholl;
	}
}