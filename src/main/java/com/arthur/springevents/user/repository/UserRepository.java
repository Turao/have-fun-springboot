package com.arthur.springevents.user.repository;

import java.util.UUID;

import com.arthur.springevents.user.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {}
