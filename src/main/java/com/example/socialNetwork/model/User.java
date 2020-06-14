package com.example.socialNetwork.model;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.socialNetwork.model.Role;
//import com.example.socialNetwork.model.Static;
@Entity // This tells Hibernate to make a table out of this class
public class User implements UserDetails {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  
  private String name;
  
  private String famile;

  private Boolean active;
  
  private String username;
  
  private String password;
  
  private String changepassword;
  
  private String activated;

  private String email;
  
  private String created;
  
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Static statistics;
  
  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;
  
  public User() {
	  
	  this.created = Long.toString(new Date().getTime());
  }

  public User(String name, String famile, String username, String password, String email ) {
	  this.name = name;
	  this.famile = famile;
	  this.username = username;
	  this.password = password;
	  this.email = email;
	  this.created = Long.toString(new Date().getTime());
  }
  
  public Map<String, Object> getProfile() {
	  Map<String, Object> model = new LinkedHashMap<>();
	  model.put("id", id);
	  model.put("name", name);
	  model.put("famile", famile);
	  model.put("username", username);
	  model.put("about", statistics);
	  return model;
  }
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

public String getFamile() {
	return famile;
}

public void setFamile(String famile) {
	this.famile = famile;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getCreated() {
	return created;
}

public void setCreated(String created) {
	this.created = created;
}

public String getActivated() {
	return activated;
}

public void setActivated(String activated) {
	this.activated = activated;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}

public Set<Role> getRoles() {
	return roles;
}

public Boolean getActive() {
	return active;
}

public void setActive(Boolean active) {
	this.active = active;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return getRoles();
}

@Override
public boolean isAccountNonExpired() {
	return true;
}

@Override
public boolean isAccountNonLocked() {
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	return true;
}

@Override
public boolean isEnabled() {
	return getActive();
}


public String getAvatar() {
	return statistics.getAvatar();
}

public Static getStatistics() {
	return statistics;
}

public String getChangepassword() {
	return changepassword;
}

public void setChangepassword(String changepassword) {
	this.changepassword = changepassword;
}





}