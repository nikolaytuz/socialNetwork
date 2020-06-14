package com.example.socialNetwork.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Image {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String url;

  private Integer likes;

  private String text;
  
  private String created;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

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

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public Integer getLikes() {
	return likes;
}

public void setLikes(Integer likes) {
	this.likes = likes;
}




}