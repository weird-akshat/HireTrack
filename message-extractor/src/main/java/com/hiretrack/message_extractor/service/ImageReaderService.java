package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.entity.SourceMessage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class ImageReaderService {
    public String extractText(SourceMessage sourceMessage){
        if (sourceMessage.getFileData() == null || sourceMessage.getFileData().length == 0) {
            throw new IllegalArgumentException("SourceMessage contains no file data");
        }
        try(ByteArrayInputStream bais = new ByteArrayInputStream(sourceMessage.getFileData())) {
            BufferedImage bufferedImage = ImageIO.read(bais);
            if (bufferedImage == null) {
                throw new IOException("Invalid image data: could not decode.");
            }

            ITesseract tesseract = new Tesseract();

            return tesseract.doOCR(bufferedImage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
