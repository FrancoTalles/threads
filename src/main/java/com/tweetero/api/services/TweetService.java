package com.tweetero.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tweetero.api.dtos.TweetDTO;
import com.tweetero.api.models.TweetModel;
import com.tweetero.api.models.UserModel;
import com.tweetero.api.repositories.TweetRepository;
import com.tweetero.api.repositories.UserRepository;


@Service
public class TweetService {
    
    final UserRepository userRepository;
    final TweetRepository tweetRepository;

    TweetService(UserRepository userRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public List<TweetModel> findAll() {
        return tweetRepository.findAll();
    }

    public List<TweetModel> findAllByUserUUID(UUID id) {

        return tweetRepository.findByUserUUID(id);
    }

    public Optional<TweetModel> save(TweetDTO dto) {
        
        Optional<UserModel> user = userRepository.findById(dto.getUserId());

        if(!user.isPresent()) {
            return Optional.empty();
        }

        TweetModel tweet = new TweetModel(dto, user.get());
        return Optional.of(tweetRepository.save(tweet));
    }
}
