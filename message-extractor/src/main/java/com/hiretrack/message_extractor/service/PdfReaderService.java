package com.hiretrack.message_extractor.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Service
public class PdfReaderService {

    public String extractText(byte[] pdfBytes) throws IOException {
        try(PDDocument document= PDDocument.load(new ByteArrayInputStream(pdfBytes))){
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);
        }
    }
}
