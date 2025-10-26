package com.hiretrack.job_management.dto;


import com.hiretrack.job_management.enums.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignupRequest {

    private  String email;
    private String password;
    private String fullName;
    private String branch;
    private double cgpa;


}
