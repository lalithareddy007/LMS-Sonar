package com.numpyninja.lms.security;

import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserLogin;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String username;  // username used to authenticate the user - In LMS it is email Id
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String username,  String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(User user, UserLogin userLogin, List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map( SimpleGrantedAuthority::new )
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUserId(),
                userLogin.getUserLoginEmail(),    // Email Id is used as Username in LMS ; so UserName is set to Email Id
                userLogin.getPassword(),
                authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(userId, user.getUserId());
    }
}
