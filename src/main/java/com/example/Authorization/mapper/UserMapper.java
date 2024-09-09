package com.example.Authorization.mapper;

import com.example.Authorization.dto.UpdateUserDto;
import com.example.Authorization.dto.UserDto;
import com.example.Authorization.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * User to UserDto.
     *
     * @param user
     * @return UserDto
     */
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "surName", source = "user.surName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "birthDate", source = "user.birthDate")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "image", expression = "java(user.getPhoto() != null ? user.getPhoto().getUrl() : \"\")")
    @Mapping(target = "role", source = "user.role")
    UserDto userToUserDto(User user);

    /**
     * UserDto to User.
     *
     * @param userDto
     * @return User
     */
    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "firstName", source = "userDto.firstName")
    @Mapping(target = "surName", source = "userDto.surName")
    @Mapping(target = "lastName", source = "userDto.lastName")
    @Mapping(target = "birthDate", source = "userDto.birthDate")
    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "phone", source = "userDto.phone")
    @Mapping(target = "role", source = "userDto.role")
    User userDtoToUser(UserDto userDto);

    /**
     * User to UpdateUserDto.
     *
     * @param user
     * @return UpdateUserDto
     */
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "surName", source = "user.surName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    UpdateUserDto userToUpdateUserDto(User user);

    /**
     * updateUserDto to User.
     *
     * @param updateUserDto
     * @return User
     */
    @Mapping(target = "firstName", source = "updateUserDto.firstName")
    @Mapping(target = "surName", source = "updateUserDto.surName")
    @Mapping(target = "lastName", source = "updateUserDto.lastName")
    @Mapping(target = "phone", source = "updateUserDto.phone")
    User updateUserDtoToUser(UpdateUserDto updateUserDto);


}

