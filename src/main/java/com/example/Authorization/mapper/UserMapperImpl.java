package com.example.Authorization.mapper;

import org.springframework.stereotype.Component;
import com.example.Authorization.dto.UpdateUserDto;
import com.example.Authorization.dto.UserDto;
import com.example.Authorization.entity.User;
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setSurName( user.getSurName() );
        userDto.setLastName( user.getLastName() );
        userDto.setBirthDate(userDto.getBirthDate());
        userDto.setEmail( user.getEmail() );
        userDto.setPhone( user.getPhone() );
        userDto.setRole( user.getRole() );

        userDto.setPhoto( user.getPhoto() != null ? user.getPhoto().getUrl() : "" );

        return userDto;
    }


    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFirstName( userDto.getFirstName() );
        user.setSurName(userDto.getSurName());
        user.setLastName( userDto.getLastName() );
        user.setBirthDate(userDto.getBirthDate());
        user.setEmail( userDto.getEmail() );
        user.setPhone( userDto.getPhone() );
        user.setRole( userDto.getRole() );

        return user;
    }


    @Override
    public UpdateUserDto userToUpdateUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UpdateUserDto updateUserDto = new UpdateUserDto();

        updateUserDto.setFirstName( user.getFirstName() );
        updateUserDto.setSurName(user.getSurName());
        updateUserDto.setLastName( user.getLastName() );
        updateUserDto.setPhone( user.getPhone() );

        return updateUserDto;
    }


    @Override
    public User updateUserDtoToUser(UpdateUserDto updateUserDto) {
        if ( updateUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( updateUserDto.getFirstName() );
        user.setSurName(updateUserDto.getSurName());
        user.setLastName( updateUserDto.getLastName() );
        user.setPhone( updateUserDto.getPhone() );

        return user;
    }
}

