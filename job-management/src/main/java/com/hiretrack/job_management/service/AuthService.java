package com.hiretrack.job_management.service;

import com.hiretrack.job_management.dto.AppUserDto;
import com.hiretrack.job_management.entity.AppUser;
import com.hiretrack.job_management.enums.Branch;
import com.hiretrack.job_management.mapper.AppUserMapper;
import com.hiretrack.job_management.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager manager;

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo appUserRepo;

    public AppUserDto getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            AppUser appUser = appUserRepo.findByEmail(((UserDetails) principal).getUsername()).orElseThrow(()->new RuntimeException());

            return AppUserMapper.toDto(appUser);
        }

        throw  new RuntimeException();
    }
    public void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
    @Transactional
    public void update(String branch, double cgpa){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            AppUser appUser = appUserRepo.findByEmail(((UserDetails) principal).getUsername()).orElseThrow(()->new RuntimeException());
            appUser.setBranch(Branch.valueOf(branch));
            appUser.setCgpa(cgpa);
            appUserRepo.save(appUser);
            return;

        }
        throw  new RuntimeException();
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
