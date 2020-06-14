package com.example.socialNetwork.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity // This tells Hibernate to make a table out of this class
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String text;

	private String image;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private Set<Likes> likes = new HashSet<>();
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();

	private String onlyFriends;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String created;

	public Post() {
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getImage() {
		if (image.length() == 0) {
			return null;
		}
		return image.split("@@-@-@@");
	}

	public void setImage(ArrayList<String> image) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < image.size(); i++) {
			stringBuilder.append(image.get(i));
			if (i != image.size() - 1) {
				stringBuilder.append("@@-@-@@");
			}
		}
		this.image = stringBuilder.toString();
	}


	public Map<Integer, Object> getAlreadyLike(){
		Map<Integer, Object> otv = new LinkedHashMap<Integer, Object>();
		for (Likes like : likes) {
				otv.put(like.getUser(), true);
		}
		return otv;
	}

	public String getOnly_friends() {
		return onlyFriends;
	}

	public void setOnly_friends(String onlyFriends) {
		this.onlyFriends = onlyFriends;
	}

	public Map<String, Object> getUser() {
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("userid", user.getId());
		model.put("username", user.getUsername());
		model.put("name", user.getName());
		model.put("famile", user.getFamile());
		model.put("avatar", user.getAvatar());
		return model;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getLikes() {
		return likes.size();
	}

	public int getComments() {
		return comments.size();
	}
}