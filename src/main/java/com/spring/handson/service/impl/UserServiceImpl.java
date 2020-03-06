package com.spring.handson.service.impl;

import com.spring.handson.exception.UserAuthFailedException;
import com.spring.handson.model.LoginDto;
import com.spring.handson.repository.UserRepository;
import com.spring.handson.model.User;
import com.spring.handson.model.UserDto;
import com.spring.handson.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		List<User> list = userRepository.findAll();
		return list.isEmpty()?new ArrayList<>():list;
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findById(String id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "username");
            userRepository.save(user);
        }
        return userDto;
    }

	@Override
	public User authenticate(LoginDto loginInfo) {
		return userRepository.findByUsernameAndPassword(loginInfo.getUsername(), loginInfo.getPassword()).orElseThrow(new UserAuthFailedException("Authentication Failed"));
	}

	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setFirstname(user.getFirstname());
	    newUser.setLastname(user.getLastname());
	    newUser.setPassword(user.getPassword());
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
        return userRepository.save(newUser);
    }
}
