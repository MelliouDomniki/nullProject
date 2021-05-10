package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.forms.UserForm;
import org.springframework.stereotype.Component;

@Component
public class UserFormMapper {

    public static User mapToUser(UserForm userForm){
        if (userForm == null) return null;
        User user = new User();
        user.setName(userForm.getName());
        user.setAddress(userForm.getAddress());
        user.setPhone_number(userForm.getPhoneNumber());
        user.setCity(userForm.getCity());
        user.setCountry(userForm.getCountry());
        user.setAvailableDoses(userForm.getAvailableDoses());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());

        if(!userForm.getUser_id().isEmpty()){
            user.setUser_id(userForm.getUser_id());
        }
        return user;

    }

    public static UserForm mapToUserForm(User user){
        if (user == null) return null;

        UserForm userForm = new UserForm();
        userForm.setUser_id(user.getUser_id());
        userForm.setName(user.getName());
        userForm.setAddress(user.getAddress());
        userForm.setPhoneNumber(user.getPhone_number());
        userForm.setCity(user.getCity());
        userForm.setCountry(user.getCountry());
        userForm.setUsername(user.getUsername());
        userForm.setEmail(user.getEmail());
        userForm.setPassword(user.getPassword());
        return userForm;
    }
}
