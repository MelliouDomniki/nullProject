package com.example.nullproject2.security.services;

import com.example.nullproject2.entity.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String hopsital_id;

    private String name;

    private String address;

    private String phone_number;

    private String city;

    private String country;

    private int available_doses;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String hospital_id, String name, String address, String phone_number, String city, String country, int available_doses, String username, String email, String password, Collection<? extends GrantedAuthority> authorities){
        this.hopsital_id = hospital_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.country = country;
        this.available_doses = available_doses;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Hospital hospital) {
        List<GrantedAuthority> authorities = hospital.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                hospital.getHospital_id(),
                hospital.getName(),
                hospital.getAddress(),
                hospital.getPhone_number(),
                hospital.getCity(),
                hospital.getCountry(),
                hospital.getAvailable_doses(),
                hospital.getUsername(),
                hospital.getEmail(),
                hospital.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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


}
