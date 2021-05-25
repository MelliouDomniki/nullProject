package com.example.nullproject2.controllers;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;



import com.example.nullproject2.entity.User;
import com.example.nullproject2.payload.JwtResponse;
import com.example.nullproject2.payload.LoginRequest;
import com.example.nullproject2.payload.MessageResponse;
import com.example.nullproject2.payload.SignupRequest;
import com.example.nullproject2.resources.VaccineController;
import com.example.nullproject2.roles.Role;
import com.example.nullproject2.roles.Erole;

import com.example.nullproject2.security.jwt.JwtUtils;
import com.example.nullproject2.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nullproject2.repositories.RoleRepository;
import com.example.nullproject2.repositories.UserRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    VaccineController vaccineController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.genertateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                userDetails.getId(),
                                                userDetails.getUsername(),
                                                userDetails.getPassword(),roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) throws ParseException {
        if (userRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse(("Error: Username is already taken!")));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: is already is use!"));
        }

        User user = new User(signupRequest.getName(),signupRequest.getAddress(),signupRequest.getPhone_number(),
                signupRequest.getCity(),signupRequest.getCountry(),
                signupRequest.getUsername(),signupRequest.getEmail(),encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();


        if (strRoles == null){
            Role userRole = roleRepository.findByName(Erole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("mod") ) {
                    Role modRole = roleRepository.findByName(Erole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    roles.add(modRole);
                }
                else{
                        Role userRole = roleRepository.findByName(Erole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return  ResponseEntity.ok(new MessageResponse("User register successfully!"));
    }

}
