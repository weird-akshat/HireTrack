package com.hiretrack.message_analyzer.message_analyzer;

import com.hiretrack.message_analyzer.message_analyzer.service.AiService;
import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiEmbeddingModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final AiService aiService;


    @Override
    public void run(String... args) throws Exception {
        String text=
"""
                Registration for Summer Internship - Boeing
                
                Trail mail for circulation among BTech CC, CS, IT, CS (AIML), DS, CSFT, MC, EC, EE, EI, CPS, EC (VLSI) AE, ME, IE, MT 2027 batch students.
                
                Company Name: Boeing India
                
                Offer Type: Summer Internship
                
                Category: Core\s
                
                Job Description: Attached
                
                Eligible Branches: BTech CC, CS, IT, CS (AIML), DS, CSFT, MC, EC, EE, EI, CPS, EC (VLSI), AE, ME, IE, MT
                
                Eligibility Criteria: 70% (10th, 12th, Diploma) BTech 7 CGPA & above, No BL
                
                Stipend: 40000 pm
                
                On Conversion CTC: 750,000 Base + Retrials +200,000 JB = 10,88,038
                
                Work Location: Bangalore/Chennai
                
                Selection Process:\s
                1. Online test: 30th October 2025
                
                2. Interview process : 31st October 2025
                
                Link to register: https://forms.gle/1xPhV9S3UJ3Kp2766
                
                Last date to register: 10 AM, 25th October 2025
                
                Please note: If the selected student secures the Pre Placement Offer (PPO) through the summer internship offered by the company, the student is not allowed for placement and internship process in the seventh semester. Further undertaking would be taken by the selected interns for the same.""";
//
            aiService.getStructuredMessage(text);

    }
}
