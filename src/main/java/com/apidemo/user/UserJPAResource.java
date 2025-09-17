package com.apidemo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apidemo.jpa.PostRepo;
import com.apidemo.jpa.UserJPARepo;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {
	private UserJPARepo repo;
	private PostRepo post_repo;
	public UserJPAResource(UserJPARepo repo, PostRepo post_repo)
	{
		this.repo = repo;
		this.post_repo = post_repo;
	}
	@GetMapping("/jpa/users")
	public List<User> getallusers()
	{
		return repo.findAll();
	}
	@GetMapping("/jpa/users/{id}")
	public Optional<User> getone(@PathVariable Integer id)
	{
		Optional<User> user = repo.findById(id);
		if(user == null)
		{
			throw new UserNotFoundException("id:"+id);
		}
		return user;
	}
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createuser(@Valid @RequestBody User user)
	{
		User saved_user = repo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved_user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteuser(@PathVariable int id)
	{
		repo.deleteById(id);
	}
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePost(@PathVariable Integer id)
	{
		Optional<User> user = repo.findById(id);
		if(user == null)
		{
			throw new UserNotFoundException("id:"+id);
		}
		return user.get().getPost();
	}
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = repo.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = post_repo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();

	}
}