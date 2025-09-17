package com.apidemo.user;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {
	
	protected User(){
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message = "Minimum two characters in name")
	private String name;
	@Past(message = "Birth Date should be in past")
	private LocalDate dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> post;
	public List<Post> getPost()
	{
		return post;
	}
	public void setPosts(List<Post> post) {
		this.post = post;
	}
	public User(Integer id, String name, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
}
