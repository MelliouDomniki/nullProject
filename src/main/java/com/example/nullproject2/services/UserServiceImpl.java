package com.example.nullproject2.services;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.forms.UserForm;
import com.example.nullproject2.mappers.UserFormMapper;
import com.example.nullproject2.mappers.UserMapper;
import com.example.nullproject2.models.UserModel;
import com.example.nullproject2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> getAllUsers() {

        return UserMapper.mapToUserModelList(userRepository.findAll());
    }

    @Override
    public Optional<UserModel> updateUser(UserForm toBeUpdatedUser) {
        User user = UserFormMapper.mapToUser(toBeUpdatedUser);
        User originaluser = userRepository.getHospital(user.getUsername());

        if (user.equals(originaluser)){
            // if no changes made dont update
            return UserMapper.mapToUserModelOptional(user);
        }
        return UserMapper.mapToUserModelOptional(userRepository.save(user));
    }

    @Override
    public boolean deleteUserById(String username) {
        if (username == null || (username).isEmpty()){
            System.out.println("Patient not found");
            return false;
        }
        userRepository.deleteByUsername(username);
        return true;
    }
}
