package com.videoStore.javaBackEnd.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videoStore.javaBackEnd.controllers.forms.UserForm;
import com.videoStore.javaBackEnd.dtos.UserDto;
import com.videoStore.javaBackEnd.models.Rental;
import com.videoStore.javaBackEnd.models.User;
import com.videoStore.javaBackEnd.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<UserDto> getAll() {
		List<User> users = userRepository.findAll();
		return UserDto.convert(users);
	}

	@Transactional
	public UserDto add(UserForm form) {
		User user = form.convert();
		userRepository.save(user);
		return new UserDto(user);
	}

	public Optional<User> getOptional(Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	public UserDto update(Long id, @Valid UserForm form) {
		User user = userRepository.getOne(id);
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		return new UserDto(user);
	}

	public void associateRental(User user) {
		User persistedUser = userRepository.getOne(user.getId());
		List<Rental> rentals = persistedUser.getRental();
		for (Rental rental : rentals) {
			rental.getVideo();
		}
	}

}
