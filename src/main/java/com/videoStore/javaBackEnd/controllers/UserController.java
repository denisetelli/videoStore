package com.videoStore.javaBackEnd.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.videoStore.javaBackEnd.controllers.forms.UserForm;
import com.videoStore.javaBackEnd.dtos.UserDto;
import com.videoStore.javaBackEnd.models.User;
import com.videoStore.javaBackEnd.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userservice;

	@GetMapping
	public List<UserDto> getAll() {
		return userservice.getAll();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> add(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		UserDto user = userservice.add(form);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserForm form) {
		Optional<User> optional = userservice.getOptional(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(userservice.update(id, form));
		}
		return ResponseEntity.notFound().build();
	}

}
