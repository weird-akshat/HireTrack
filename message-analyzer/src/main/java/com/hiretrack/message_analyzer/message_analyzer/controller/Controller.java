package com.hiretrack.message_analyzer.message_analyzer.controller;

import com.hiretrack.message_analyzer.message_analyzer.dto.InputMessage;
import com.hiretrack.message_analyzer.message_analyzer.service.AiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {
    private final AiService aiService;
    @PostMapping
    public ResponseEntity<HttpStatus> analyze(@RequestBody List<InputMessage> list){
        System.out.println(list.get(0).getSourceId());
        System.out.println(list);
        try{
            for (InputMessage inputMessage : list){
                aiService.getStructuredMessage(inputMessage.toString());
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }

}
