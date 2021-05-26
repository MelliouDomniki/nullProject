package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.enumerations.UserStatus;
import com.example.nullproject2.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserModel mapToUserModel(User user){
        if (user == null) return null;
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setAddress(user.getAddress());
        userModel.setPhone_number(user.getPhone_number());
        userModel.setCity(user.getCity());
        userModel.setCountry(user.getCountry());
        userModel.setAvailableDoses(user.getAvailableDoses());
        userModel.setUsername(user.getUsername());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setTransactionStatus(user.getTransactionStatus());
        return userModel;
    }

    public static List<UserModel> mapToUserModelList(List<User> users){
        return users.stream().map(UserMapper::mapToUserModel)
                .collect(Collectors.toList());
    }

    public static Optional<UserModel> mapToUserModelOptional(User user){
        if (user == null) return Optional.empty();
        return Optional.of(mapToUserModel(user));
    }
}
