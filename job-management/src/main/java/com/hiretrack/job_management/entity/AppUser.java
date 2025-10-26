package com.hiretrack.job_management.entity;


import com.hiretrack.job_management.enums.Branch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String fullName;
    private String password;
    private String role;
    private Branch branch;
    private double cgpa;

}
