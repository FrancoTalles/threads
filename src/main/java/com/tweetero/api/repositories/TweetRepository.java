package com.tweetero.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tweetero.api.models.TweetModel;

@Repository
public interface TweetRepository extends JpaRepository<TweetModel, UUID> {
    
    @Query(value = "SELECT * FROM tweets where user_id = :userId", nativeQuery = true)
    List<TweetModel> findByUserUUID(@Param("userId") UUID userId);
}
