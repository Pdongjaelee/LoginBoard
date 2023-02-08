package com.example.callbus.Grobal.security;

import java.util.Collections;

import com.example.callbus.Member.Realtor.repository.RealtorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.callbus.Member.Realtor.entity.Realtor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RealtorRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Realtor member = memberRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Not Found Account")
        );
        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Realtor member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("member");

        return new User(
                member.getEmail(),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}

