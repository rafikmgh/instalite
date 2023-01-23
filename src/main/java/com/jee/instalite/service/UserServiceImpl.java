package com.jee.instalite.service;

import com.jee.instalite.model.Role;
import com.jee.instalite.model.User;
import com.jee.instalite.repositories.UserRepository;
import com.jee.instalite.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto registrationDto) {
        User user = new User(registrationDto.getUsername() , passwordEncoder.encode(registrationDto.getPassword()),registrationDto.getEmail() , registrationDto.getRoles());

        return userRepository.save(user) ;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {

        String newUsername = userDto.getUsername();
        String newEmail = userDto.getEmail();
        String newPassword = passwordEncoder.encode(userDto.getPassword()) ;


        User user = userRepository.findById(id).get();

        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        userRepository.save(user);

    }

    @Override
    public void deleteUser(Long id) {
      userRepository.deleteById(id);
    }

    @Override
    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles() ));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Collection<Role> roles) {

       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
