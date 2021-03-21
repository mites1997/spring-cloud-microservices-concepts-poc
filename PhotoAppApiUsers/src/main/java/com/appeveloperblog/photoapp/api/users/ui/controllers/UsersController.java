package com.appeveloperblog.photoapp.api.users.ui.controllers;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appeveloperblog.photoapp.api.users.shared.UserDto;
import com.appeveloperblog.photoapp.api.users.service.UsersService;
import com.appeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.appeveloperblog.photoapp.api.users.ui.model.CreateUserResponseModel;
import com.appeveloperblog.photoapp.api.users.ui.model.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private Environment env;
	@Autowired
	UsersService usersService;
	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port "+env.getProperty("local.server.port")+"with token = "+env.getProperty("token.secret");
		
	}
	@PostMapping(consumes= {MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON},produces= {MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails)
	{
		ModelMapper modelmapper=new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto=modelmapper.map(userDetails, UserDto.class);
		UserDto createdUser=usersService.createUser(userDto);
		CreateUserResponseModel returnvalue=modelmapper.map(createdUser, CreateUserResponseModel.class);
		return  ResponseEntity.status(HttpStatus.CREATED).body(returnvalue);
		
	}
	@GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
       
        UserDto userDto = usersService.getUserByUserId(userId); 
        UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
        
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
