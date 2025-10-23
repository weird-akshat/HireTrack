package com.hiretrack.message_analyzer.message_analyzer.service;

import com.hiretrack.message_analyzer.message_analyzer.client.EntityManagerClient;
import com.hiretrack.message_analyzer.message_analyzer.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hiretrack.message_analyzer.message_analyzer.enums.ResponseType.*;

@Slf4j
@Service
public class AiService {
    private final ChatClient chatClient;
    private final EntityManagerClient entityManagerClient;
    public AiService(ChatClient.Builder chatClientBuilder, EntityManagerClient entityManagerClient){
        this.chatClient = chatClientBuilder.build();
        this.entityManagerClient= entityManagerClient;
    }
    public void analyze(List<InputMessage> list){
        try{
            for (InputMessage inputMessage : list){
                getStructuredMessage(inputMessage.toString());
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public void getStructuredMessage(String text){
        String prompt = "Extract relevant fields; normalize company names (remove suffixes like 'OT','Inc.'); abbreviate branches (no spaces, exclude 'BTech'); parse all dates into LocalDateTime (no timezone), even from natural language; always create a notification object with concise message (detailed only if objectType='notification'); select proper enum; leave unknowns null; classify job listing changes (OT/interview postponement, new details) as 'jobupdate'; always ensure JSON is properly closed and minimal; if a jobUpdate exists, still include a notification; responseType should be NOTIFICATION only if no other object apart from notification is being created. If a message begins with caption: xxx.pdf format, classify it as job update and make it a job update object. If a message has registration link given, classify it as joblistig and make it a joblisting object. If it's a Job description, classify it as job update and create a jobupdate object not a joblisting.";
        AiResponse aiResponse;
        try{
            aiResponse = chatClient.prompt(text).user(u->u.text(prompt)).call().entity(AiResponse.class);
            if (aiResponse==null){
                throw new RuntimeException("AI response Null");
            }
            log.info("Response returned from LLM: {}", aiResponse.toString());
        }
        catch (Exception e){
            log.error("AI Response Error");
            throw new RuntimeException("ERROR: AI RESPONSE");
        }


        if (aiResponse.getResponseType()==JOB_LISTING){
            log.info("JOB LISTING: : {}", aiResponse.getJobListing().toString());
            log.info("Sending to message extractor.");
            ApiResponse response = entityManagerClient.createJobListing(aiResponse.getJobListing());
            log.info("Request completed");
            log.info("Entity Manager response: {}", response.toString());
        }
        if (aiResponse.getResponseType() == JOB_UPDATE){
            log.info("Sending request to entity manager for updating the job.");
            log.info("JOB UPDATE: {}", aiResponse.getJobUpdate().toString());
            JobUpdateListing jobUpdateListing= entityManagerClient.link(aiResponse.getJobUpdate());
            String updatePrompt = "Given this job Listing object, update the fields according to the new update that has been given.";
            try{

                JobUpdateListing newJobUpdateListing= chatClient.prompt(jobUpdateListing.toString()+text).user(u->u.text(updatePrompt)).call().entity(JobUpdateListing.class);

                if (newJobUpdateListing==null){
                    log.error("JobUpdateListing not created by the llm.");
                    throw new RuntimeException("JobUpdateListing not created by the llm.");
                }
                log.info("JOB UPDATE LISTING CREATED: {}",newJobUpdateListing.toString());


                log.info("Sending the new job update listing to entity manager");
                ApiResponse apiResponse = entityManagerClient.updateJob(newJobUpdateListing);
                log.info(apiResponse.toString());




            }
            catch (Exception e){
                log.error(e.getMessage());
            }
        }
        if (aiResponse.getResponseType()==SHORTLIST){
            log.info("Shortlist: {}", aiResponse.getShortlist().toString());
            try{
                log.info("Send request to create shortlist to entity manager.");
                ApiResponse apiResponse = entityManagerClient.createShortlist(aiResponse.getShortlist());
                log.info("Shortlist created successfully\nResponse from entity manager {}",apiResponse.toString());


            }
            catch (Exception e){
                log.error("Error in creating shortlist.");
                throw new RuntimeException();
            }
        }
        try{
            log.info("Sending request to entity manager to create notification.");
            ApiResponse apiResponse= entityManagerClient.createNotification(aiResponse.getJobNotification());
            log.info("Created notification successfully. \nEntity manager response: {}", apiResponse.toString());

        }
        catch (Exception e){
            log.info("Issue in creating notification");
            throw new RuntimeException();
        }


    }


}
