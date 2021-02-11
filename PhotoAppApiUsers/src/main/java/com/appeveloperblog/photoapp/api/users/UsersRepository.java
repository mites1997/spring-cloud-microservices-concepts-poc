package com.appeveloperblog.photoapp.api.users;

import org.springframework.data.repository.CrudRepository;

import com.appeveloperblog.photoapp.api.users.data.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	
}
