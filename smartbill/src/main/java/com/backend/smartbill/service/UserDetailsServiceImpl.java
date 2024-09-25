package com.backend.smartbill.service;

import com.backend.smartbill.model.UserModel;
import com.backend.smartbill.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = this.userRepository.findByName(username);
        if (userModel == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userModel.getName(), userModel.getPassword(), new ArrayList<>());
    }

}
