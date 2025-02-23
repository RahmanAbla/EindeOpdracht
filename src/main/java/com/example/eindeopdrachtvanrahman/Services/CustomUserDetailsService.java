package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.UserDTO;
import com.example.eindeopdrachtvanrahman.models.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServise userServise;

    public CustomUserDetailsService(UserServise userServise) {
        this.userServise = userServise;
    }



    @Override
    public UserDetails loadUserByUsername(String username){
        UserDTO userDTO=userServise.getUser(username);
        String password=userDTO.getPassword();
        Set<Authority>authorities=userDTO.getAuthorities();
        List<GrantedAuthority>grantedAuthorities=new ArrayList<>();
        for (Authority authority:authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(username,password,grantedAuthorities);
    }
}
