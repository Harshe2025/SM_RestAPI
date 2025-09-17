package com.apidemo.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDao service;
	public UserResource(UserDao service)
	{
		this.service = service;
	}
	@GetMapping("/users")
	public List<User> getallusers()
	{
		return service.findAll();
	}
	@GetMapping("/users/{id}")
	public User getone(@PathVariable Integer id)
	{
		User user = service.findOne(id);
		if(user == null)
		{
			throw new UserNotFoundException("id:"+id);
		}
		return user;
	}
	@PostMapping("/users")
	public ResponseEntity<User> createuser(@Valid @RequestBody User user)
	{
		User saved_user = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved_user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/users/{id}")
	public void deleteuser(@PathVariable int id)
	{
		service.deletebyId(id);
	}
}