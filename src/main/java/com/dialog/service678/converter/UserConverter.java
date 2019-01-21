package com.dialog.service678.converter;

import com.dialog.service678.dto.UserDto;
import com.dialog.service678.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static User  dtoToEntity(UserDto userDto){

        User user = new User();
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static UserDto entityToDto(User user){

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
