package com.example.nullproject2.security;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

import com.example.nullproject2.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {


    private String hospital_id;
    private String name;
    private String address;
    private String phone_number;
    private String city;
    private String country;
    private int available_doses;
    private String email;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String hospital_id, String username, String address, String phone_number, int available_doses, String email, String password, String city, String country, List<GrantedAuthority> authorities) {

    }

    public static UserDetailsImpl build(Hospital user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getHospital_id(),
                user.getUsername(),
                user.getAddress(),
                user.getPhone_number(),
                user.getAvailable_doses(),
                user.getEmail(),
                user.getPassword(),
                user.getCity(),
                user.getCountry(),
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
        return Objects.equals(hospital_id, user.hospital_id);
    }

}
