package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements UserDetailsService to find a User by his username and made some checks
 *
 * @see UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Load a user by his username
     *
     * @param username user's username
     *
     * @return a user
     * @throws UsernameNotFoundException exception if the user isn't find
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur ayant pour pseudo " + username + " n'a pas été trouvé"));
        return UserDetailsImpl.build(user);
    }

    /**
     * Method to check if a user exists with the given username
     *
     * @param username username to check
     *
     * @return boolean
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Method to check if a user exists with the given email
     *
     * @param email email to check
     *
     * @return boolean
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * To save a user
     *
     * @param user the user to save
     */
    public void save(User user) {
        userRepository.save(user);
    }
}
