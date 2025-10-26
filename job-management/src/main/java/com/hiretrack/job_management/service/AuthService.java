package com.hiretrack.job_management.service;

import com.hiretrack.job_management.entity.AppUser;
import com.hiretrack.job_management.enums.Branch;
import com.hiretrack.job_management.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager manager;

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo appUserRepo;
    public void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    public void signup(String fullName, String email, String password, String branch, double cgpa){

        try{
//            log.info("Request received");

            AppUser user = AppUser.builder().fullName(fullName).email(email).password(passwordEncoder.encode(password)).branch(Branch.valueOf(branch)).cgpa(cgpa).role("USER").build();
            log.info("User made");
            if (appUserRepo.findByEmail(email).isPresent()){
//                throw new RuntimeException("User already exists");
            }
            else{
                appUserRepo.save(user);

                doAuthenticate(email, password);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Exception: "+e);
        }


    }
}
