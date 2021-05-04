package com.example.nullproject2.services;

import com.example.nullproject2.forms.UserForm;
import com.example.nullproject2.models.UserModel;
import com.example.nullproject2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository hospitalRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserModel> addUser(UserForm user) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<UserModel> updateUser(UserForm toBeUpdatedUser) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUserById(String user_id) {
        return false;
    }
}
