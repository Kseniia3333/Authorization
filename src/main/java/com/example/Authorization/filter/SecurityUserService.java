package com.example.Authorization.filter;

import com.example.Authorization.dto.Role;
import com.example.Authorization.entity.User;
import com.example.Authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsManager {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findUserByEmail(userName);
        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        User user = byEmail.get();
        return new MyUserDetails(user);

    }

    @Override
    public void createUser(UserDetails user) {
        User user1 = new User(user.getUsername(), user.getPassword(),
                Role.valueOf(user.getAuthorities().iterator().next().getAuthority().substring(5)));
        userRepository.save(user1);
    }

    public void createUser(UserDetails user, String firstName, String surName, String lastName, String phone) {
        User user1 = new User(user.getUsername(), user.getPassword(), Role.valueOf(user.getAuthorities().iterator().next().getAuthority().substring(5)));
        user1.setFirstName(firstName);
        user1.setSurName(surName);
        user1.setLastName(lastName);
        user1.setPhone(phone);
        userRepository.save(user1);

    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findUserByEmail(username).isPresent();
    }
}




