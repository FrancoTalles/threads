package com.tweetero.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetero.api.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{
    boolean existsByUsername (String username);
}
