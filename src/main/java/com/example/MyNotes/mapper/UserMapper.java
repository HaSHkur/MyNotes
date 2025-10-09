package com.example.MyNotes.mapper;

import com.example.MyNotes.domain.UserDomain;
import com.example.MyNotes.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDomain entityToDomain(User user) {
        if (user == null) {
            return null;
        }
        return new UserDomain(
                user.getUsername(),
                user.getEmail(),
                user.getName()
        );
    }

    public User domainToEntity(UserDomain domain) {
        if (domain == null) {
            return null;
        }
        User user = new User();
        user.setUsername(domain.getUsername());
        user.setEmail(domain.getEmail());
        user.setName(domain.getName());
        return user;
    }
}

