package com.jee.instalite.service;

import com.jee.instalite.model.User;
import com.jee.instalite.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(UserDto userDto);

    Optional<User> getUserById (Long id);

    List<User> getAllUsers();

    void updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    User authenticateUser(String email, String password);

}
