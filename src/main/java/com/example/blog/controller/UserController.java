package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.entities.User;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
	private UserService userService;
    
	//POST
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {  
    	UserDto create=this.userService.createUser(userDto);
		return new ResponseEntity<>(create,HttpStatus.CREATED);
    	
    }
    
	//GET
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId )
    {
    	
		return ResponseEntity.ok(this.userService.getUserById(userId));
    	
    }
	//PUT
	
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
	//UPDATE
    {   
    	return ResponseEntity.ok(this.userService.updateUser(userDto, userId));
    }
    
   //DELETE
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deletUser(@PathVariable("userId") Integer userId)
    {
		 this.userService.deleteUser(userId);
		 return new ResponseEntity(new ApiResponse("user deleted",true), HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAlluser()
    {
		return ResponseEntity.ok(this.userService.getAllUsers());
    	
    }
}
