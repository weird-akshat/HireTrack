package com.hiretrack.job_management.mapper;

import com.hiretrack.job_management.dto.AppUserDto;
import com.hiretrack.job_management.entity.AppUser;

public class AppUserMapper {

    public static AppUserDto toDto(AppUser appUser){
        return AppUserDto.builder()
                .email(appUser.getEmail())
                .fullName(appUser.getFullName())
                .branch(appUser.getBranch().name())
                .cgpa(appUser.getCgpa())
                .build();
    }
}
