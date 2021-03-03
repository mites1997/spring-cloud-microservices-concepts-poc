package com.appeveloperblog.photoapp.api.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appeveloperblog.photoapp.api.users.shared.UserDto;
import com.appeveloperblog.photoapp.api.users.shared.UsersRepository;
import com.appeveloperblog.photoapp.api.users.data.UserEntity;
@Service
public class UsersServicempl implements UsersService {
	
	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	public UsersServicempl(UsersRepository usersRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.usersRepository=usersRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelmapper=new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity=modelmapper.map(userDetails, UserEntity.class);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		usersRepository.save(userEntity);
		UserDto returnval=modelmapper.map(userEntity, UserDto.class);
		return returnval;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity=usersRepository.findByEmail(username);
		if(userEntity==null)
			throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
		
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		UserEntity userEntity=usersRepository.findByEmail(email);
		if(userEntity==null)
			throw new UsernameNotFoundException(email);
		return new ModelMapper().map(userEntity,UserDto.class);
	}

	

}
