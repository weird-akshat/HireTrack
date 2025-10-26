package com.hiretrack.job_management.service;

import com.hiretrack.job_management.dto.JobListingDto;
import com.hiretrack.job_management.dto.JobNotificationDto;
import com.hiretrack.job_management.dto.ShortlistDto;
import com.hiretrack.job_management.entity.AppUser;
import com.hiretrack.job_management.entity.JobListing;
import com.hiretrack.job_management.entity.JobNotification;
import com.hiretrack.job_management.entity.Shortlist;
import com.hiretrack.job_management.mapper.JobListingMapper;
import com.hiretrack.job_management.mapper.JobNotificationMapper;
import com.hiretrack.job_management.mapper.ShortlistMapper;
import com.hiretrack.job_management.repo.AppUserRepo;
import com.hiretrack.job_management.repo.JobListingRepo;
import com.hiretrack.job_management.repo.JobNotificationRepo;
import com.hiretrack.job_management.repo.ShortlistRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobManagementService {
    private final AppUserRepo appUserRepo;
    private final JobListingRepo jobListingRepo;
    private final JobNotificationRepo jobNotificationRepo;
    private final ShortlistRepo shortlistRepo;
    public List<JobListingDto> getJobListings(){
        log.info("Finding Job Listings");
        List<JobListing>  jobListings = jobListingRepo.findAll();
        return jobListings.stream()
                .map(JobListingMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<JobListingDto> getUserJobListing(){
        log.info("Finding Job Listings");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            AppUser user = appUserRepo.findByEmail(((UserDetails) principal).getUsername()).orElseThrow(()->new RuntimeException());

            List<JobListing>  jobListings = jobListingRepo.findByMinCGPALessThanAndEligibleBranchesContaining(user.getCgpa(), user.getBranch().name());
            return jobListings.stream()
                    .map(JobListingMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw new RuntimeException();

    }
    public List<JobNotificationDto> getAllJobNotifications(){
        List<JobNotification> jobNotifications = jobNotificationRepo.findAll();

        return jobNotifications.stream().map(JobNotificationMapper::toDto).collect(Collectors.toList());
    }
    public List<JobNotificationDto> getJobNotifications(Long jobId){
        List<JobNotification> jobNotifications = jobNotificationRepo.findByJobListingId(jobId);
        return jobNotifications.stream().map(JobNotificationMapper::toDto).collect(Collectors.toList());

    }
   public List<ShortlistDto> getShortlists(Long jobId){
        List<Shortlist> shortlists = shortlistRepo.findByJobListingId(jobId);

        return shortlists.stream().map(ShortlistMapper::toDto).collect(Collectors.toList());
   }

}
