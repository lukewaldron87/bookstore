package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.User;

public interface UserMapService {

    User mapDtoToNewEntity(UserDto userDto);
    User mapDtoToExistingEntity(UserDto sourceUserDto, User targetUserEntity);
    UserDto mapEntityToNewDto(User user);
}
