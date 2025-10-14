package com.hiretrack.message_extractor;

import com.hiretrack.message_extractor.Entity.SourceMessage;
import com.hiretrack.message_extractor.Repo.SourceMessageRepo;
import com.hiretrack.message_extractor.Service.PdfReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    private final PdfReaderService pdfReaderService;
    private final SourceMessageRepo sourceMessageRepo;
    @Override
    public void run(String... args) throws IOException {
        // Load PDF from the same directory as Runner
        File pdfFile = new File("sample.pdf"); // put your PDF here
        if (!pdfFile.exists()) {
            System.out.println("PDF file not found: " + pdfFile.getAbsolutePath());
            return;
        }

        byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());

        // Create SourceMessage
        SourceMessage message = SourceMessage.builder()
                .contentType("application/pdf")
                .size(pdfBytes.length)
                .timeStamp(LocalDateTime.now())
                .fileData(pdfBytes)
                .build();

        // Test PDF reading
        String text = pdfReaderService.extractText(message.getFileData());
        System.out.println("Extracted text:\n" + text);
//        sourceMessageRepo.save(message);
        System.out.println("PDF loaded successfully. Size: " + message.getSize() + " bytes");
    }
}
