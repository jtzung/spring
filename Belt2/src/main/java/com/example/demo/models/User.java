package com.example.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @NotNull
 @Size(min=3, message="Name can not be null")
 private String name;
 @Email(message="Email must be valid")
 private String email;
 @Size(min=8, message="Password must be greater than 8 characters")
 private String password;
 @Transient
 private String passwordConfirmation;
 @OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
 private List<Idea> idea;
 @ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinTable(
			name="user_ideas",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="ideas_id")
			)
	private List<Idea> like_idea;
 
 @Column(updatable=false)
 private Date createdAt;
 private Date updatedAt;
 
 public User() {
 
 }
 
 
 
public List<Idea> getIdea() {
	return idea;
}



public void setIdea(List<Idea> idea) {
	this.idea = idea;
}



public List<Idea> getLike_idea() {
	return like_idea;
}



public void setLike_idea(List<Idea> like_idea) {
	this.like_idea = like_idea;
}



public Long getId() {
	return id;
}





public void setId(Long id) {
	this.id = id;
}


public String getName() {
	return name;
}

public void setName(String name) {
	this.name=name;
}

public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public String getPasswordConfirmation() {
	return passwordConfirmation;
}



public void setPasswordConfirmation(String passwordConfirmation) {
	this.passwordConfirmation = passwordConfirmation;
}



public Date getCreatedAt() {
	return createdAt;
}



public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}



public Date getUpdatedAt() {
	return updatedAt;
}



public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}



@PrePersist
 protected void onCreate(){
     this.createdAt = new Date();
 }
 @PreUpdate
 protected void onUpdate(){
     this.updatedAt = new Date();
 }
}