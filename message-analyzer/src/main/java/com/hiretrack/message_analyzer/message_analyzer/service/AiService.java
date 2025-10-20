package com.hiretrack.message_analyzer.message_analyzer.service;

import com.hiretrack.message_analyzer.message_analyzer.client.EntityManagerClient;
import com.hiretrack.message_analyzer.message_analyzer.dto.AiResponse;
import com.hiretrack.message_analyzer.message_analyzer.dto.JobUpdate;
import com.hiretrack.message_analyzer.message_analyzer.dto.JobUpdateListing;
import com.hiretrack.message_analyzer.message_analyzer.dto.Shortlist;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import static com.hiretrack.message_analyzer.message_analyzer.enums.ResponseType.*;

@Service
public class AiService {
    private final ChatClient chatClient;
    private final EntityManagerClient entityManagerClient;
    public AiService(ChatClient.Builder chatClientBuilder, EntityManagerClient entityManagerClient){
        this.chatClient = chatClientBuilder.build();
        this.entityManagerClient= entityManagerClient;
    }

    public void getStructuredMessage(String text){
        String prompt = "Extract relevant fields; normalize company names (remove suffixes like 'OT','Inc.'); abbreviate branches (no spaces, exclude 'BTech'); parse all dates into LocalDateTime (no timezone), even from natural language; always create a notification object with concise message (detailed only if objectType='notification'); select proper enum; leave unknowns null; classify job listing changes (OT/interview postponement, new details) as 'jobupdate'; always ensure JSON is properly closed and minimal; if a jobUpdate exists, still include a notification; responseType should be NOTIFICATION only if no other object apart from notification is being created. If a message begins with caption: xxx.pdf format, classify it as job update and make it a job update object. If a message has registration link given, classify it as joblistig and make it a joblisting object. If it's a Job description, classify it as job update and create a jobupdate object not a joblisting.";

        AiResponse aiResponse = chatClient.prompt(text).user(u->u.text(prompt)).call().entity(AiResponse.class);

        System.out.println(aiResponse.getResponseType());
        System.out.println(aiResponse.getJobListing());
        System.out.println(aiResponse.getJobUpdate());
        System.out.println(aiResponse.getJobNotification());
        Shortlist shortlist = aiResponse.getShortlist();
        if (shortlist!=null){
            System.out.println(shortlist.getStudentDetails());
        }
        if (aiResponse.getResponseType()==JOB_LISTING){
            entityManagerClient.createJobListing(aiResponse.getJobListing());
        }
        if (aiResponse.getResponseType() == JOB_UPDATE){

            JobUpdateListing jobUpdateListing= entityManagerClient.link(aiResponse.getJobUpdate());

            System.out.println("Returned this brotherr");
            System.out.println(jobUpdateListing.toString());
            String updatePrompt = "Given this job Listing object, update the fields according to the new update that has been given.";
            JobUpdateListing newJobUpdateListing= chatClient.prompt(jobUpdateListing.toString()+text).user(u->u.text(prompt)).call().entity(JobUpdateListing.class);
            newJobUpdateListing.setId(jobUpdateListing.getId());
            newJobUpdateListing.setCompanyName(jobUpdateListing.getCompanyName());
            newJobUpdateListing.setSourceId(jobUpdateListing.getSourceId());
//            newJobUpdateListing.setOfferType(jobUpdateListing.getOfferType());
//            newJobUpdateListing.setCategory(jobUpdateListing.getCategory());
            System.out.println(newJobUpdateListing.toString());
            entityManagerClient.updateJob(newJobUpdateListing);


        }
        if (aiResponse.getResponseType()==SHORTLIST){

            entityManagerClient.createShortlist(aiResponse.getShortlist());
            System.out.println("Yoohoo");
        }
        entityManagerClient.createNotification(aiResponse.getJobNotification());

    }


}
