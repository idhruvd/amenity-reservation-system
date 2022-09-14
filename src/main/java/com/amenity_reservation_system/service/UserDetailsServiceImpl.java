package com.amenity_reservation_system.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amenity_reservation_system.model.User;
import com.amenity_reservation_system.repos.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findUserByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getPasswordHash()).roles("USER").build();
        return userDetails;
    }
    
}
