package com.tweetero.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.dtos.TweetDTO;
import com.tweetero.api.models.TweetModel;
import com.tweetero.api.models.UserModel;
import com.tweetero.api.services.TweetService;
import com.tweetero.api.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tweets")
public class TweetController {
    
    final UserService userService;
    final TweetService tweetService;

    TweetController(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<List<TweetModel>> getTweets() {
        List<TweetModel> tweets = tweetService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getTweetsByUserUUID(@PathVariable UUID id){

        Optional <UserModel> user = userService.findById(id);

        if(!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe/encontrado");
        }

        List<TweetModel> userTweets = tweetService.findAllByUserUUID(id);

        return ResponseEntity.status(HttpStatus.OK).body(userTweets);
    }

    @PostMapping
    public ResponseEntity<Object> createTweet(@RequestBody @Valid TweetDTO body) {
        Optional<TweetModel> tweet = tweetService.save(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }
}
