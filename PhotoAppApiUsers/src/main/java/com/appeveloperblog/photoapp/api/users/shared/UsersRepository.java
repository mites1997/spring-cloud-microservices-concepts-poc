package com.appeveloperblog.photoapp.api.users.shared;

import org.springframework.data.repository.CrudRepository;

import com.appeveloperblog.photoapp.api.users.data.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
}
