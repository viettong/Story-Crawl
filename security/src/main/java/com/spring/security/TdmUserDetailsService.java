package com.spring.security;

import com.spring.security.models.UserEntity;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TdmUserDetailsService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOption = userRepository.findByUsername(username);
        userOption.orElseThrow(()->new UsernameNotFoundException("Not Found "+ username));
        return userOption.map(TdmUserDetail::new).get();
    }
}
