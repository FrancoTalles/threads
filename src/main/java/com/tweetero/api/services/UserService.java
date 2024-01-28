package com.tweetero.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tweetero.api.dtos.UserDTO;
import com.tweetero.api.models.UserModel;
import com.tweetero.api.repositories.UserRepository;

import lombok.NonNull;

@Service
public class UserService {
    final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(@NonNull UUID id) {
        return userRepository.findById(id);
    }

    public Optional<UserModel> save(UserDTO dto) {
        if(userRepository.existsByUsername(dto.getUsername())) {
            return Optional.empty();
        }

        UserModel user = new UserModel(dto);
        return Optional.of(userRepository.save(user));
    }

    public UserModel update(UUID id, UserDTO dto) {
        UserModel newUser = new UserModel(dto);
        newUser.setId(id);
        return userRepository.save(newUser);
    }

    public void deleteById(@NonNull UUID id) {
        userRepository.deleteById(id);
    }
}
