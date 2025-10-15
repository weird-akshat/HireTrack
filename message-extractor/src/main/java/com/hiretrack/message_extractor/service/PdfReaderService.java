package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.entity.SourceMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Service
public class PdfReaderService {

    public String extractText(SourceMessage sourceMessage) throws IOException {
        try(PDDocument document= PDDocument.load(new ByteArrayInputStream(sourceMessage.getFileData()))){
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);
        }
    }
}
