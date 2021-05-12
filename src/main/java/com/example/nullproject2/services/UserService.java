package com.example.nullproject2.services;



import com.example.nullproject2.forms.UserForm;
import com.example.nullproject2.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> getAllUsers();

    Optional<UserModel> addUser(UserForm user) throws Exception;

    Optional<UserModel> updateUser(UserForm toBeUpdatedUser);

    boolean deleteUserById(String user_id);

}
