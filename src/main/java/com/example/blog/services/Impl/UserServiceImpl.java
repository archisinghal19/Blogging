package com.example.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Exceptions.ResourceNotFoundException;
import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User saved=this.userRepo.save(user);
		return this.userToDto(saved);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
			User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		 user.setName(userDto.getName());
		 user.setEmail(userDto.getEmail());
		 user.setPassword(userDto.getPassword());
		 User update=this.userRepo.save(user);
		 UserDto userDto1=this.userToDto(update);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
     User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
	 this.userRepo.delete(user);
	}
   
	private User dtoToUser(UserDto userDto)
	{
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
		
	}
}
