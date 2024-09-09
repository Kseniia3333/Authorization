package com.example.Authorization.service.impl;

import com.example.Authorization.filter.SecurityUserService;
import com.example.Authorization.service.AuthentificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {

        private static final Logger logger = LoggerFactory.getLogger(AuthentificationServiceImpl.class);
        private final PasswordEncoder encoder;
        private final SecurityUserService manager;


    public AuthentificationServiceImpl(PasswordEncoder encoder, SecurityUserService manager) {
            this.encoder = encoder;
            this.manager = manager;
        }

        @Override
        public boolean login(String userName, String password) {
            if (!manager.userExists(userName)) {
                logger.info("AuthService login function if user not exists");
                return false;
            }

            UserDetails userDetails = manager.loadUserByUsername(userName);
            logger.info("Password in login: {}, Password in DB: {}", password, userDetails.getPassword());
            return encoder.matches(password, userDetails.getPassword());

        }

    }

